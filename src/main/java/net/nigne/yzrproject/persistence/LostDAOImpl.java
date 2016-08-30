package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.ReservationVO;
import net.nigne.yzrproject.domain.ReviewVO;

@Repository
public class LostDAOImpl implements LostDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long getTotalCount(String member_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(long.class);
		Root<LostVO> root = cq.from(LostVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(cb.count(root.get("member_id")));
		cq.where(p);
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		long totalcount = tq.getSingleResult();
		return totalcount;
	}


	@Override
	public List<LostVO> getListPage(String member_id, Criteria criteria) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<LostVO> cq = cb.createQuery(LostVO.class);
		Root<LostVO> root = cq.from(LostVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(root);
		cq.where(p).orderBy(cb.asc(root.get("complete")),cb.desc(root.get("no")));
		TypedQuery<LostVO> tq = entityManager.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());
		List<LostVO> lostpagelist = tq.getResultList();
		return lostpagelist;
	}


	@Override
	public List<LostVO> getLostSearch(String member_id, String search,Criteria criteria) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<LostVO> cq = cb.createQuery(LostVO.class);
		Root<LostVO> root = cq.from(LostVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		Predicate s = cb.like(root.get("lost_content"), "%"+search+"%");
		cq.select(root).where(cb.and(p,s));
		TypedQuery<LostVO> tq = entityManager.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());;
		List<LostVO> lostsearchlist = tq.getResultList();
		return lostsearchlist;
	}



	@Override
	public long getSearchTotal(String member_id, String search) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(long.class);
		Root<LostVO> root = cq.from(LostVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		Predicate s = cb.like(root.get("lost_content"), "%"+search+"%");
		cq.select(cb.count(root.get("member_id")));
		cq.where(cb.and(p,s));
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		long totalcount = tq.getSingleResult();
		return totalcount;
	}


	@Override
	public MemberVO getMember(String member_id) {
		MemberVO vo=new MemberVO();
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq=cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("member_id"), member_id));
		TypedQuery<MemberVO> tq = entityManager.createQuery(cq);
		vo=tq.getSingleResult();
		
		return vo;
	}


	@Override
	public LostVO getLost(int no) {
		// TODO Auto-generated method stub
		LostVO vo=new LostVO();
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<LostVO> cq=cb.createQuery(LostVO.class);
		Root<LostVO> root = cq.from(LostVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("no"),no));
		TypedQuery<LostVO> tq = entityManager.createQuery(cq);
		vo=tq.getSingleResult();
		
		return vo;
	}
}
