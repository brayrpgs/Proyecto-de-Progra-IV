package cr.ac.una.booleanKitchen.Logica;

import java.time.Duration;
import java.time.LocalDateTime;

public class LogicaGeneral {
    public static String calcularAntiguedad(LocalDateTime fechaPublicacion) {
        LocalDateTime ahora = LocalDateTime.now();
        Duration duracion = Duration.between(fechaPublicacion, ahora);

        if (duracion.toDays() < 1) {
            long horas = duracion.toHours();
            if (horas < 1) {
                long minutos = duracion.toMinutes();
                if (minutos < 1) {
                    return "Justo ahora";
                } else {
                    return "Hace " + minutos + " minuto" + (minutos == 1 ? "" : "s");
                }
            } else {
                return "Hace " + horas + " hora" + (horas == 1 ? "" : "s");
            }
        } else if (duracion.toDays() < 7) {
            long dias = duracion.toDays();
            return "Hace " + dias + " día" + (dias == 1 ? "" : "s");
        } else if (duracion.toDays() < 30) {
            long semanas = duracion.toDays() / 7;
            return "Hace " + semanas + " semana" + (semanas == 1 ? "" : "s");
        } else {
            long meses = duracion.toDays() / 30;
            return "Hace " + meses + " mes" + (meses == 1 ? "" : "es");
        }
    }

}
