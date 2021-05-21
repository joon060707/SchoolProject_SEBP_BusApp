package domain.busline;

import javax.persistence.*;
import java.util.List;

public class BusLineTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =em.getTransaction();

        try{
            tx.begin();
            logic(em);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
    private static void logic(EntityManager em){
        Integer id = 1234;
        BusLine busLine = new BusLine.Builder()
                .id(id)
                .name("전남대1")
                .build();

        Integer id2 = 4567;
        BusLine busLine2 = new BusLine.Builder()
                .id(id2)
                .name("전남대2")
                .build();

        //등록
        em.persist(busLine);
        em.persist(busLine2);

//        //전체 조회
        List<BusLine> busLineList = em.createQuery("select b from BusLine b", BusLine.class).getResultList();
        System.out.println(busLineList);
        for(BusLine bl : busLineList){
            System.out.println(bl.getId());
            System.out.println(bl.getName());
        }

        //id로 삭제
        BusLine foundBusStop =em.find(BusLine.class, id);
        em.remove(foundBusStop);
    }
}
