package pt.iade.helloworldEIT1.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.helloworldEIT1.models.CurricularUnit;


@RestController
@RequestMapping(path="/api/java/tester")

public class JavaTesterController {
    private Logger logger = LoggerFactory.getLogger(GreeterController.class);  //como se fosse o System.out mas é bem formatado
    private ArrayList<CurricularUnit> units = new ArrayList<CurricularUnit>();
    private double grades[] = {10.5, 12, 14.5};
    private String ucs[] = {"FP","POO","BD"};

    @PostMapping(path = "/units", produces= MediaType.APPLICATION_JSON_VALUE)
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit) {
       logger.info("Added unit "+unit.getName());
    units.add(unit);
       return unit;
    }

    @GetMapping(path = "/units",
    produces= MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnits() {
       logger.info("Get" +units.size()+ " Units");
       return units;
    }

    @GetMapping(path = "/access/{student}/{covid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent, @PathVariable("covid") boolean hasCovid) {
        logger.info("Is student: " + isStudent + ", has covid: " +hasCovid);
        return isStudent && !hasCovid;
    }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}",
    produces= MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent, @PathVariable("temperature") double hasCovid,
    @PathVariable("classType") String type) {
       return isStudent && (hasCovid > 34.5 && hasCovid < 37.5) && (type.equals("presential"));
    }

    @GetMapping(path = "/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}/",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getEvacuation(@PathVariable("fire") boolean fire, @PathVariable("numberOfCovids") int numberOfCovids,
    @PathVariable("powerShutdown") boolean powerShutdown, @PathVariable("comeBackTime") int comeBackTime) {
       return fire && (numberOfCovids > 5 || powerShutdown) && comeBackTime > 15;
    }

    @GetMapping(path = "/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public double grade() {
       double soma = 0;
       for (int i=0; i<grades.length; i++) {
          soma += grades[i];
       }
       double grade = soma / (double) grades.length;
       return grade;
    }

    //   Return the maximum grade
    @GetMapping(path = "/maiorNota", produces = MediaType.APPLICATION_JSON_VALUE)
    public double maiorNota() {
     double maior = 0;
     for (int i = 1; i < grades.length; i++) {
        if (grades[i] >= maior) {
            maior= grades[i];
         }
      }
      return maior;
    }

// Given the name of the uc return the grade
    @GetMapping(path = "visualizar/grade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double Grade() {
     String uc = "FP";
     double grade = 3;
     for (int i = 0; i < ucs.length; i++) {
        if (ucs[i].equals(uc)) {
            grade = grades[i];
        }
      }
      return grade;
    }

//   Given minimum and maximum grade, return how many UCs have grades in those limits  

    @GetMapping(path = "/LimGrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double LimGrade() {
     double min = 4.6;
     double max = 11.7;
     double tot = 0;
     for (int i = 0; i < ucs.length; i++) {
        if (grades[i] >= min && grades[i] <= max) {
            tot++;
        }
     }
     return tot;
    }

// Return a string with a text with all UC names and grades
   @GetMapping(path = "ucs/notas", produces = MediaType.APPLICATION_JSON_VALUE)
   public String Info() {
     String info = "";
     for (int i = 0; i < ucs.length; i++) {
        info += "  (" +ucs[i] + " - " + grades[i] + ")  "; 
     }
     return info;
   }
    
}
