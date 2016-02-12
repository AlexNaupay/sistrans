package com.devteam.sistrans.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Component
public class SistransUtils {

    private static final String DATE_INPUT_FORMAT_DEFAULT = "yyyyMMdd";
    private static final String HOUR_INPUT_FORMAT_DEFAULT = "HHmmss";
    private static final String DATE_DB_FORMAT="yyyyMMdd";
    private static final String HOUR_DB_FORMAT="HHmmss";

    private static final int DECIMALS_NUMBER_DEFAULT = 2;

    private static Properties properties;

    @Resource(name = "config")
    private Properties propertie;

    @PostConstruct
    public void init(){
        properties = propertie;
    }

    /**
     * Valida un campo según su tipo
     * @param value El valor a validar
     * @param type El valor se espera de este tipo : fecha, hora, numerico, real
     * @return boolean
     */
    public static boolean syntaxValidate(String value, String type) {
        if ("fecha".equals(type.toLowerCase())){ //Fecha
            return isDate(value);
        }else if ("hora".equals(type.toLowerCase())){ //Hora
            return isHour(value);
        }else if("digito".equals(type.toLowerCase())){ //Digito
            return isDigit(value);
        }else if ("char".equals(type.toLowerCase())){
            return isString(value);
        }
        return false;
    }

    /**
     * Verfica si la fecha cumple con el formato esperado
     * @param date fecha en cadena
     * @return true|false
     */
    public static boolean isDate(String date) {
        String formatoFecha = properties.getProperty("date.input.format", DATE_INPUT_FORMAT_DEFAULT);
        return parseDateTime(formatoFecha, date);
    }

    /**
     * Verfica si la hora es correcta
     * @param hour La hora en cadena
     * @return true | false
     */
    public static boolean isHour(String hour) {
        String formatoHora = properties.getProperty("hour.input.format", HOUR_INPUT_FORMAT_DEFAULT);
        return parseDateTime(formatoHora, hour);

    }

    /**
     * Verifica si la datetime cumple con el formato format
     * @param format Formato que debe cumplir dateOrTime
     * @param dateOrTime La fecha o la hora a parsear
     * @return true | false
     */
    private static boolean parseDateTime(String format, String dateOrTime){
        try{
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
            LocalTime.parse(dateOrTime,timeFormatter);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Verifica que la cadena contenga unicamente numeros
     * @param digits numero en cadena
     * @return true or false
     */
    public static boolean isDigit(String digits) {
        return NumberUtils.isDigits(digits);
    }

    /**
     * Verifica que los caracteres sean unicamente alfanumericos
     * @param string cadena
     * @return true or false
     */
    public static boolean isString(String string){
        return StringUtils.isAlphanumeric(string);
    }

    /**
     * Convierte el número en cadena a un docuble
     * @param stringDouble double en cadena
     * @return docuble
     */
    public static double stringToDouble(String stringDouble) {
        int numeroDecimales;
        try {
            numeroDecimales = Integer.parseInt(properties.getProperty("sistrans.decimals.number"));
        }catch (Exception e){
            numeroDecimales = DECIMALS_NUMBER_DEFAULT;
        }
        StringBuilder stringBuilderDouble = new StringBuilder(stringDouble);
        stringBuilderDouble.insert(stringDouble.length()-numeroDecimales,'.');
        return Double.parseDouble(stringBuilderDouble.toString());
    }

    public static String dateToDbFormat(String fecha){
        LocalDate date = LocalDate.parse(fecha,
                DateTimeFormatter.ofPattern(properties.getProperty("date.input.format",DATE_INPUT_FORMAT_DEFAULT)));
        return date.format(DateTimeFormatter.ofPattern(DATE_DB_FORMAT));
    }

    public static String hourToDbFormat(String hour){
        LocalTime time = LocalTime.parse(hour,
                DateTimeFormatter.ofPattern(properties.getProperty("hour.input.format",HOUR_INPUT_FORMAT_DEFAULT)));
        return time.format(DateTimeFormatter.ofPattern(HOUR_DB_FORMAT));
    }

}
