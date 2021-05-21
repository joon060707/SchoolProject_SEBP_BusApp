package service;

import Dto.BusLineRequestDto;
import Dto.BusStopRequestDto;
import domain.busline.BusLine;
import domain.busstop.BusStop;

import javax.persistence.*;
import java.util.List;

public class FavoritesService {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public FavoritesService(){}


    public void save(BusLineRequestDto requestDto) {
        try {
            tx.begin();
            em.persist(requestDto.toEntity());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

    }

    public void save(BusStopRequestDto requestDto) {
        try {
            tx.begin();
            em.persist(requestDto.toEntity());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void delById(BusLineRequestDto requestDto) {
        try {
            tx.begin();
            BusLine foundBusLine = em.find(BusLine.class, requestDto.getId());
            em.remove(foundBusLine);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void delById(BusStopRequestDto requestDto) {
        try {
            tx.begin();
            BusStop foundBusStop = em.find(BusStop.class, requestDto.getId());
            em.remove(foundBusStop);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    public List<BusLine> findAllLines() {
        try {
            tx.begin();
            List<BusLine> busLineList = em.createQuery("SELECT bl FROM BusLine bl", BusLine.class).getResultList();
            tx.commit();
            return busLineList;

        } catch (Exception e) {
            tx.rollback();
        }
        return null;
    }

    public List<BusStop> findAllStops() {
        try {
            tx.begin();
            List<BusStop> BusStopList = em.createQuery("SELECT bs FROM BusStop bs", BusStop.class).getResultList();
            tx.commit();
            return BusStopList;

        } catch (Exception e) {
            tx.rollback();
        }
        return null;
    }

    public void deleteAllStop(){
        try {
            tx.begin();
            Query query = em.createQuery("delete from BusStop");
            query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }
    public void deleteAllLine(){
        try {
            tx.begin();
            Query query = em.createQuery("delete from BusLine");
            query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }
}
