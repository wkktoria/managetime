package io.github.wkktoria.managetime.controller;

import io.github.wkktoria.managetime.logic.ProjectService;
import io.github.wkktoria.managetime.model.ProjectStep;
import io.github.wkktoria.managetime.model.projection.ProjectWriteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
class ProjectController {
    private final ProjectService service;

    ProjectController(final ProjectService service) {
        this.service = service;
    }

    @GetMapping
    String showProjects(Model model) {
        model.addAttribute("project", new ProjectWriteModel());
        return "projects";
    }

    @PostMapping
    String addProject(@ModelAttribute("project") ProjectWriteModel project, Model model) {
        service.save(project);
        model.addAttribute("project", new ProjectWriteModel());
        model.addAttribute("message", "Project added successfully.");
        return "projects";
    }

    @PostMapping(params = "addStep")
    String addProjectStep(@ModelAttribute("project") ProjectWriteModel currentProject) {
        currentProject.getSteps().add(new ProjectStep());
        return "projects";
    }
}