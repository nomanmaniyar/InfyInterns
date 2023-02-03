package com.infy.infyinterns.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;

@Service(value = "projectService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private MentorRepository mentorRepository;

	@Override
	public Integer allocateProject(ProjectDTO project) throws InfyInternException {

		Optional<Mentor> mOptional = mentorRepository.findById(project.getMentorDTO().getMentorId());
		Mentor mentor = mOptional.orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));
		if (mentor != null && mentor.getNumberOfProjectsMentored() >= 3) {
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		} else {
			Project project2 = new Project();
			project2.setIdeaOwner(project.getIdeaOwner());
			project2.setMentor(mentor);
			project2.setProjectId(project.getProjectId());
			project2.setProjectName(project.getProjectName());
			project2.setReleaseDate(project.getReleaseDate());
			project2.getMentor().setNumberOfProjectsMentored(
					project2.getMentor().getNumberOfProjectsMentored() + 1);
			Project project3 = projectRepository.save(project2);
			return project3.getProjectId();
		}

	}

	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {

		
		return null;
	}

	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {

	}

	@Override
	public void deleteProject(Integer projectId) throws InfyInternException {

	}
}