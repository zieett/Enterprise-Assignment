//package assignment.service;
//import assignment.entity.*;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * Created by CoT on 10/13/17.
// */
//
//@Transactional
//public class Service {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public void setupDatabase(){
//        ArrayList<Drug> drugs = new ArrayList<>();
//        ArrayList<assignment.entity.Producer> producers = new ArrayList<>();
//        try {
//            File myObj = new File("message.txt");
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                String[] split = data.split("&&");
//                boolean isExist = false;
//                for (assignment.entity.Producer value : producers) {
//                    if (value.getId().equals(split[9])) {
//                        isExist = true;
//                        break;
//                    }
//                }
//                if (!isExist) {
//                    assignment.entity.Producer producer = new assignment.entity.Producer();
//                    producer.setId(split[9]);
//                    producer.setName(split[8]);
//                    producers.add(producer);
//                }
//            }
//            myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                String[] split = data.split("&&");
//                for (assignment.entity.Producer producer : producers) {
//                    if (producer.getId().equals(split[9])) {
//                        Drug drug = new Drug(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], producer, split[10]);
//                        drugs.add(drug);
//                        break;
//                    }
//                }
//            }
//            myReader.close();
//        } catch (FileNotFoundException ignored) {}
//
//        for (assignment.entity.Producer producer: producers) {
//            sessionFactory.getCurrentSession().save(producer);
//        }
//        for (Drug drug: drugs) {
//            sessionFactory.getCurrentSession().save(drug);
//        }
//    }
//
//    public List saveDrug(Drug drug){
//        List<String> result = new ArrayList<>();
//        for (Object o : Handler.drugs) {
//            Drug drug1 = (Drug) o;
//            if (drug1.getId().equals(drug.getId())) {
//                result.add("failed");
//                return result;
//
//            }
//        }
//        sessionFactory.getCurrentSession().save(drug);
//        result.add("success");
//        return result;
//    }
//
//
//    public List getDrugsList(){
//        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
//        CriteriaQuery<Drug> query = builder.createQuery(Drug.class);
//        Root<Drug> root = query.from(Drug.class);
//        query.select(root);
//        Query<Drug> q = sessionFactory.getCurrentSession().createQuery(query);
//        return q.getResultList();
//    }
//    public List getProducersList(){
//        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
//        CriteriaQuery<assignment.entity.Producer> query = builder.createQuery(assignment.entity.Producer.class);
//        Root<assignment.entity.Producer> root = query.from(assignment.entity.Producer.class);
//        query.select(root);
//        Query<assignment.entity.Producer> q = sessionFactory.getCurrentSession().createQuery(query);
//        return q.getResultList();
//    }
//
//    public List getDrugs(){
//        Handler.drugs = getDrugsList();
//        return Handler.drugs;
//    }
//
//    public List getProducers(){
//        Handler.producers = getDrugsList();
//        return Handler.producers;
//    }
//
//    public List updateDrugMoney(String id, int money){
//        List<String> result = new ArrayList<>();
//        for (Object o : Handler.drugs) {
//            Drug drug = (Drug) o;
//            if (drug.getId().equals(id)) {
//                drug.setMoney(money);
//                sessionFactory.getCurrentSession().update(drug);
//                Handler.drugs = getDrugs();
//                result.add("success");
//                return result;
//            }
//        }
//        result.add("failed");
//        return result;
//    }
//
//    public List searchDrugsById(String id){
//        List<String> l = new ArrayList<>();
//        for (Object o : Handler.drugs) {
//            Drug drug = (Drug) o;
//            if (drug.getId().contains(id)) {
//                l.add(drug.toString());
//            }
//        }
//        return l;
//    }
//
//    public List searchDrugsByName(String name){
//        List<String> result = new ArrayList<>();
//        for (Object o : Handler.drugs) {
//            Drug drug = (Drug) o;
//            if (drug.getName().contains(name)) {
//                result.add(drug.toString());
//            }
//        }
//        return result;
//    }
//
//    public List getNOP (String producerID){
//        List<String> result = new ArrayList<>();
//        int count = 0;
//        for (Object o : Handler.producers) {
//            Drug drug = (Drug) o;
//            if (drug.getProducers().getId().equals(producerID)) {
//                count++;
//            }
//        }
//        result.add(Integer.toString(count));
//        return result;
//    }
//
//    public List getAllGroups(){
//        List<String> result = new ArrayList<>();
//        for (int i = 0; i < Handler.drugs.size(); i++) {
//            Drug drug = (Drug) Handler.drugs.get(i);
//            if (!result.contains(drug.getDrugGroup())) {
//                result.add(drug.getDrugGroup());
//            }
//        }
//        return result;
//    }
//
//    public List getAllTypes(){
//        List<String> result = new ArrayList<>();
//        for (int i = 0; i < Handler.drugs.size(); i++) {
//            Drug drug = (Drug) Handler.drugs.get(i);
//            if (!result.contains(drug.getType()) && !drug.getType().equals(" ") && !drug.getType().equals("--")) {
//                result.add(drug.getDrugGroup());
//            }
//        }
//        result.add("Undefined");
//        return result;
//    }
//
//    public List saveProducer(assignment.entity.Producer newProducer){
//        List<String> result = new ArrayList<>();
//        for (Object o : Handler.producers) {
//            assignment.entity.Producer producer = (assignment.entity.Producer) o;
//            if (producer.getId().equals(newProducer.getId())) {
//                result.add("failed");
//                return result;
//            }
//        }
//        sessionFactory.getCurrentSession().save(newProducer);
//        result.add("success");
//        return result;
//    }
//
//    public List deleteDrug(String id){
//        List<String> result = new ArrayList<>();
//        for (Object o : Handler.drugs) {
//            Drug drug = (Drug) o;
//            if (drug.getId().equals(id)) {
//                sessionFactory.getCurrentSession().delete(drug);
//                Handler.drugs = getDrugs();
//                result.add("success");
//                return result;
//            }
//        }
//        result.add("failed");
//        return result;
//    }
//}
