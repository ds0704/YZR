package net.nigne.yzrproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.GpaVO;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.ReviewVO;
import net.nigne.yzrproject.service.LostService;
import net.nigne.yzrproject.service.QnaService;
import net.nigne.yzrproject.service.RentService;

@Controller
public class UserQuestionController {
	@Autowired
	private LostService lost_Service;
	@Autowired
	private RentService rent_Service;
	@Autowired
	private QnaService qna_Service;

	//1:1
	@RequestMapping(value = "/user/qna", method = RequestMethod.GET)
	public ModelAndView userQnaPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/qna");
		return view;
	}
	
	@RequestMapping(value = "/user/qna/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> qnalistPage(HttpServletRequest request,
			@PathVariable("page") Integer page,@RequestParam("search") String search   ) {
		ResponseEntity<Map<String, Object>> entity = null;
		HttpSession session=request.getSession();
		String member_id=(String)session.getAttribute("member_id");
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<QnaVO> list = null;
			if("".equals(search)){
				list=qna_Service.getListPage(member_id, criteria);
				long qnaTotal = qna_Service.getTotalCount(member_id);
				PageMaker pm = new PageMaker(criteria, qnaTotal);
				map.put("l", list);
				map.put("p", pm);
			}else{
				list=qna_Service.getQnaSearch(member_id, search, criteria);
				long qnasearchTotal = qna_Service.getSearchTotal(member_id, search);
				PageMaker pm = new PageMaker(criteria, qnasearchTotal);
				map.put("l", list);
				map.put("p", pm);
			}
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	
	@RequestMapping(value = "/user/qna/read/{no}", method = RequestMethod.GET)
	public String qna_read(Locale locale, Model model, @PathVariable("no") int no,HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		String member_id=(String)session.getAttribute("member_id");
	
		
		MemberVO membervo = qna_Service.getMember(member_id);
		model.addAttribute("membervo", membervo);
		

		QnaVO qnavo = qna_Service.getQna(no);
		model.addAttribute("qnavo", qnavo);

		return "/user/qna_read";
	}
	
	
	//분실문문의
	@RequestMapping(value = "/user/lost", method = RequestMethod.GET)
	public ModelAndView userLostPage() throws Exception {
	
		ModelAndView view=new ModelAndView();
		view.setViewName("user/lost");
		return view;
	}
	//
	@RequestMapping(value = "/user/lost/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> lostlistPage(HttpServletRequest request,
			@PathVariable("page") Integer page,@RequestParam("search") String search   ) {
		ResponseEntity<Map<String, Object>> entity = null;
		HttpSession session=request.getSession();
		String member_id=(String)session.getAttribute("member_id");
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<LostVO> list = null;
			if("".equals(search)){
				list=lost_Service.getListPage(member_id, criteria);
				long lostTotal = lost_Service.getTotalCount(member_id);
				PageMaker pm = new PageMaker(criteria, lostTotal);
				map.put("l", list);
				map.put("p", pm);
			}else{
				list=lost_Service.getLostSearch(member_id, search, criteria);
				long lostsearchTotal = lost_Service.getSearchTotal(member_id, search);
				PageMaker pm = new PageMaker(criteria, lostsearchTotal);
				map.put("l", list);
				map.put("p", pm);
			}
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	
	
	@RequestMapping(value = "/user/lost/read/{no}", method = RequestMethod.GET)
	public String lost_read(Locale locale, Model model, @PathVariable("no") int no,HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		String member_id=(String)session.getAttribute("member_id");
	
		
		MemberVO membervo = lost_Service.getMember(member_id);
		model.addAttribute("membervo", membervo);
		

		LostVO lostvo = lost_Service.getLost(no);
		model.addAttribute("lostvo", lostvo);

		return "/user/lost_read";
	}
	//
	
	
	
		
	//대관문의
	@RequestMapping(value = "/user/rent", method = RequestMethod.GET)
	public ModelAndView userRentPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/rent");
		return view;
	}
	
	@RequestMapping(value = "/user/rent/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> rentlistPage(HttpServletRequest request,
			@PathVariable("page") Integer page,@RequestParam("search") String search   ) {
		ResponseEntity<Map<String, Object>> entity = null;
		HttpSession session=request.getSession();
		String member_id=(String)session.getAttribute("member_id");
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<RentVO> list = null;
			if("".equals(search)){
				list=rent_Service.getListPage(member_id, criteria);
				long lostTotal = rent_Service.getTotalCount(member_id);
				PageMaker pm = new PageMaker(criteria, lostTotal);
				map.put("l", list);
				map.put("p", pm);
			}else{
				list=rent_Service.getRentSearch(member_id, search, criteria);
				long lostsearchTotal = rent_Service.getSearchTotal(member_id, search);
				PageMaker pm = new PageMaker(criteria, lostsearchTotal);
				map.put("l", list);
				map.put("p", pm);
			}
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	@RequestMapping(value = "/user/rent/read/{no}", method = RequestMethod.GET)
	public String rent_read(Locale locale, Model model, @PathVariable("no") int no,HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		String member_id=(String)session.getAttribute("member_id");
	
		
		MemberVO membervo = rent_Service.getMember(member_id);
		model.addAttribute("membervo", membervo);
		

		RentVO rentvo = rent_Service.getRent(no);
		model.addAttribute("rentvo", rentvo);

		return "/user/rent_read";
	}
}