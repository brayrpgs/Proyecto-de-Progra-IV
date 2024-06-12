package cr.ac.una.booleanKitchen.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cr.ac.una.booleanKitchen.domain.ShopList;

@Service
public class RestClientService {

    public PageResponse<ShopList> getClient(int numPage,RestTemplate t ) {
        String url = "http://localhost:8080/shoplist/mostrar/" + numPage;
        ResponseEntity<PageResponse<ShopList>> response = t.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<PageResponse<ShopList>>() {}
        );
        return response.getBody();
    }

    
    /* 
    public Page<ShopList> getClient(int numPage, RestTemplate t) {
        String url = "http://localhost:8080/shoplist/mostrar/"+numPage;
        Page<ShopList> page = t.getForObject(url, PageResponse.class);
        return page;
    }
        */
}
