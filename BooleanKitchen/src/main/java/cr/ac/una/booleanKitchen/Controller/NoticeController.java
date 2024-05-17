package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.domain.Notice;
import cr.ac.una.booleanKitchen.service.NoticeRepository;
import cr.ac.una.booleanKitchen.service.NoticeService;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/c_noticia")
public class NoticeController {

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping(value = "/noticias")
	public String findAll(@PageableDefault(size = 4, page = 0) Pageable pageable, Model model) {
		Page<Notice> page = noticeRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

		model.addAttribute("page", page);
		var totalPages = page.getTotalPages();
		var currentPage = page.getNumber();

		var start = Math.max(1, currentPage);
		var end = Math.min(currentPage + 5, totalPages);

		if (totalPages > 0) {
			List<Integer> pageNumbers = new ArrayList<>();
			for (int i = start; i <= end; i++) {
				pageNumbers.add(i);
			}

			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "notice/notice_index";
	}

	@GetMapping("noticias/agregarNoticias")
	public String addNotice() {

		return "notice/notice_add";
	}

	@PostMapping("/getNotice")
	public String getNotice(@RequestParam("image") MultipartFile img,
			@ModelAttribute Notice notice, Model model) {

		notice.setRUTA_IMG(img.getOriginalFilename());
		Optional<Notice> existingNoticeByIdentificador = noticeService.findByIdentificador(notice.getIDENTIFICADOR());
		Optional<Notice> existingNoticeByURl = noticeService.findByURL(notice.getURL());

		if (existingNoticeByIdentificador.isPresent()) {

			model.addAttribute("message",
					"El identificador de la noticia ingresada ya se encuentra registrada \n" +
							"!Por favor intenta nuevamente con otro identificador!");
			return "notice/messageNotice";

		} else if (existingNoticeByURl.isPresent()) {

			model.addAttribute("message",
					"La Url de la noticia ingresada ya se encuentra registrada \n" +
							"!Por favor intenta nuevamente con otra Url!");
			return "notice/messageNotice";

		} else {

			if (notice.getAUTHOR().trim().equals("")) {
				notice.setAUTHOR("Anónimo");
			}

			String imageToIngredient = noticeService.uploadImage(img);
			notice.setRUTA_IMG(imageToIngredient);

			noticeService.saveNotice(notice);
			model.addAttribute("message",
					"!La noticia ingresada se insertó correctamente!");

			return "notice/messageNotice";

		}

	}

	@GetMapping("noticias/actualizarNoticia")
	public String Update(@RequestParam("IDENTIFICADOR") String IDENTIFICADOR, Model model) throws SQLException {

		Notice myNotice = noticeService.findByIdentificador(IDENTIFICADOR).get();

		model.addAttribute("item", myNotice);

		return "notice/update_Notice";

	}

	@PostMapping("/updateNotice")
	public String updateNotice(@RequestParam("image") MultipartFile img,
			@ModelAttribute Notice notice, Model model) {


		// Recupero la noticia vieja
		Notice myNotice = noticeService.findByIdentificador(notice.getIDENTIFICADOR()).get();
		
		Optional<Notice> existingNoticeByURl = noticeService.findByURL(notice.getURL());

		if(existingNoticeByURl.isPresent() && !existingNoticeByURl.get().equals(myNotice)){

			model.addAttribute("message", "La URL de la noticia ingresada ya se encuentra registrada \n" +
                    "!Por favor intenta nuevamente con otra URL!");
            return "notice/messageNotice";

		} else {

			//Obtengo el url de la imagen vieja
			String oldImageName = myNotice.getRUTA_IMG();
			//Elimino la imagen de la carpeta
			noticeService.deleteImage(oldImageName);

			//Seteo los nuevos datos de mi registro 
			myNotice.setTITULO(notice.getTITULO());
			myNotice.setRESUMEN(notice.getRESUMEN());
			myNotice.setFECHA(notice.getFECHA());
			
			if (myNotice.getAUTHOR().trim().equals("")) {
				
				myNotice.setAUTHOR("Anónimo");
			} else {

				myNotice.setAUTHOR(notice.getAUTHOR());

			}
			
			myNotice.setESTADO(notice.getESTADO());
			myNotice.setURL(notice.getURL());

			//Guardo la nueva imagen y su ruta
			String newRouteImg = noticeService.uploadImage(img);
			myNotice.setRUTA_IMG(newRouteImg);

			//Actualizo el registro
			noticeService.saveNotice(myNotice);

			model.addAttribute("message", "!La noticia ingresada se actualizó correctamente!");
            return "notice/messageNotice";

		}

		

	}


	@PostMapping("/deleteNotice")
	public String deleteNotice(@PageableDefault(size = 4, page = 0) Pageable pageable, Model model,
	@RequestParam("ID") Integer ID,@RequestParam("RUTA_IMG") String url){
		
		//Eliminar la noticia de la base de datos
		noticeService.deleteNoticeByID(ID);
		//Eliminar la imagen de los directorios
		noticeService.deleteImage(url);
		
		//Actualizo las noticias y su paginacion

		Page<Notice> page = noticeRepository
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

		model.addAttribute("page", page);
		var totalPages = page.getTotalPages();
		var currentPage = page.getNumber();

		var start = Math.max(1, currentPage);
		var end = Math.min(currentPage + 5, totalPages);

		if (totalPages > 0) {
			List<Integer> pageNumbers = new ArrayList<>();
			for (int i = start; i <= end; i++) {
				pageNumbers.add(i);
			}

			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "notice/resultTable";

	}

	@GetMapping("noticias/verNoticia")
	public String showNotice(@RequestParam("URL") String URL, Model model){

		//Recupero mi noticia de la base de datos
		Notice myNotice = noticeService.findByURL(URL).get();

		//La agrego al html para poder ver su informacion
		model.addAttribute("notice", myNotice);

		return "notice/show_notice";

	}

}

