package net.nigne.yzrproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.TimetableVO;
import net.nigne.yzrproject.service.AdminEtcService;
import net.nigne.yzrproject.service.AdminTimetableService;

@Controller
public class AdminTimetableController {

	@Autowired
	private AdminTimetableService service;

	@RequestMapping(value = "/admin/timetable", method = RequestMethod.GET)
	public ModelAndView adminTimetablePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.addObject("theaterlist", service.getTheatername());
		view.setViewName("admin/timetable");
		return view;
	}
	@RequestMapping(value = "/admin/timetable/new", method = RequestMethod.GET)
	public ModelAndView adminWriteTimetablePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.addObject("moviename_list", service.getMoviename());
		view.addObject("theatername_list", service.getTheatername());
		view.addObject("plexnumber_list", service.getPlexnumber());
		view.setViewName("admin/timetableWrite");
		return view;
	}
	@RequestMapping(value = "/admin/timetable/new", method = RequestMethod.POST)
	public ModelAndView adminWritetimetable(@RequestParam("movie_id") String movie_id, @RequestParam("theater_id") String theater_id,
											@RequestParam("plex_number") String plex_number, @RequestParam("year") String year,
											@RequestParam("month") String month, @RequestParam("day") String day,
											@RequestParam("hour") String hour, @RequestParam("minute") String minute) throws Exception{
		if(Integer.parseInt(month)<10){
			month="0" + month;
		}
		if(Integer.parseInt(month)<10){
			day="0" + day;
		}
		if(Integer.parseInt(month)<10){
			hour="0" + hour;
		}
		if(Integer.parseInt(month)<10){
			minute="0" + minute;
		}
		String start_time=year + "-" + month + "-" + day + " " + hour + ":" + minute;
		TimetableVO vo=new TimetableVO();
		vo.setMovie_id(movie_id);
		vo.setTheater_id(theater_id);
		vo.setPlex_number(plex_number);
		vo.setStart_time(start_time);
	
		service.persist(vo);
		
		return new ModelAndView("redirect:/admin/timetable");
	}
}