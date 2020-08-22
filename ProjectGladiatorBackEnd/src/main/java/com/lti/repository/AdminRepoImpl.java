package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.model.Admin;

@Repository
public class AdminRepoImpl implements AdminRepo {

	@PersistenceContext
	EntityManager em;

	@Override
	public String isValidAdmin(String adminEmail, String adminPassword) {

		String sql = "select admin from Admin admin";
		Query qry = em.createQuery(sql);

		List<Admin> admin = qry.getResultList();

		for (Admin a : admin) {
			if (a.getAdminEmail().equals(adminEmail) && adminPassword.equals(a.getAdminPassword()))
				return a.getAdminEmail();
		}

		return null;
	}

	@Override
	public Admin findAdminByEmail(String adminEmail) {
		return em.find(Admin.class, adminEmail);
	}

}
