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
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.QnaVO;

@Repository
public class QnaDAOImpl implements QnaDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long getTotalCount(String member_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(long.class);
		Root<QnaVO> root = cq.from(QnaVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(cb.count(root.get("member_id")));
		cq.where(p);
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		long totalcount = tq.getSingleResult();
		return totalcount;
	}


	@Override
	public List<QnaVO> getListPage(String member_id, Criteria criteria) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<QnaVO> cq = cb.createQuery(QnaVO.class);
		Root<QnaVO> root = cq.from(QnaVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(root);
		cq.where(p).orderBy(cb.desc(root.get("no")));
		TypedQuery<QnaVO> tq = entityManager.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());
		List<QnaVO> qnapagelist = tq.getResultList();
		return qnapagelist;
	}


	@Override
	public List<QnaVO> getQnaSearch(String member_id, String search,Criteria criteria) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<QnaVO> cq = cb.createQuery(QnaVO.class);
		Root<QnaVO> root = cq.from(QnaVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		Predicate s = cb.like(root.get("title"), "%"+search+"%");
		cq.select(root).where(cb.and(p,s));
		TypedQuery<QnaVO> tq = entityManager.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());;
		List<QnaVO> qnasearchlist = tq.getResultList();
		return qnasearchlist;
	}



	@Override
	public long getSearchTotal(String member_id, String search) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(long.class);
		Root<QnaVO> root = cq.from(QnaVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		Predicate s = cb.like(root.get("content"), "%"+search+"%");
		cq.select(cb.count(root.get("member_id")));
		cq.where(cb.and(p,s));
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		long totalcount = tq.getSingleResult();
		return totalcount;
	}


	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
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
	public QnaVO getQna(int no) {
		// TODO Auto-generated method stub
		QnaVO vo=new QnaVO();
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<QnaVO> cq=cb.createQuery(QnaVO.class);
		Root<QnaVO> root = cq.from(QnaVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("no"),no));
		TypedQuery<QnaVO> tq = entityManager.createQuery(cq);
		vo=tq.getSingleResult();
		
		return vo;
	}}
