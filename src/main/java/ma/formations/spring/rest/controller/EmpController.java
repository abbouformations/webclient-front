package ma.formations.spring.rest.controller;

import lombok.AllArgsConstructor;
import ma.formations.spring.rest.domaine.EmpVo;
import ma.formations.spring.rest.service.IService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class EmpController {
private IService service;

    @RequestMapping(value="/")
    public String showWelcomeFile(Model m) {
        // loads 1 and display 1, stream data, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(service.getAllEmployees(), 1);

        m.addAttribute("employees", reactiveDataDrivenMode);

        // classic, wait repository loaded all and display it.
        //model.addAttribute("movies", movieRepository.findAll());

        return "index";
    }

    @GetMapping("/add")
    public String showAddForm( Model m) {
        m.addAttribute("emp", new EmpVo());
        return "add";
    }


    @PostMapping("/create")
    public String addEmployee(EmpVo emp,
                            BindingResult result, Model m) {
        service.createEmployee(emp).subscribe();
        return "redirect:/";
    }

    @GetMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model m) {
        Mono<EmpVo> emp = service.getEmployeeById(id);
        m.addAttribute("emp",emp );
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmp( EmpVo emp,
                            Model m) {
        service.updateEmployee(emp.getId(), emp).subscribe();
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") long id, Model model) {
        service.deleteEmployee(id).subscribe();
        return "redirect:/";
    }
}