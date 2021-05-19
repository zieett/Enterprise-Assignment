package main.java.service;
import main.java.entity.Drug;
import main.java.entity.Producers;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by CoT on 10/13/17.
 */

@Transactional
public class Service {
    @Autowired
    private SessionFactory sessionFactory;

    public void setupDatabase(){
        ArrayList<Drug> drugs = new ArrayList<>();
        ArrayList<Producers> producers = new ArrayList<>();
        try {
            File myObj = new File("message.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                boolean isExist = false;
                for (Producers value : producers) {
                    if (value.getId().equals(split[9])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Producers producer = new Producers();
                    producer.setId(split[9]);
                    producer.setName(split[8]);
                    producers.add(producer);
                }
            }
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("&&");
                for (Producers producer : producers) {
                    if (producer.getId().equals(split[9])) {
                        Drug drug = new Drug(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], producer, split[10]);
                        drugs.add(drug);
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ignored) {}

        for (Producers producer: producers) {
            sessionFactory.getCurrentSession().save(producer);
        }
        for (Drug drug: drugs) {
            sessionFactory.getCurrentSession().save(drug);
        }
    }

    public List saveDrug(Drug drug){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug1 = (Drug) o;
            if (drug1.getId().equals(drug.getId())) {
                result.add("failed");
                return result;

            }
        }
        for (Object o : Handler.producers) {
            Producers producers = (Producers) o;
            if (producers.getId().equals(drug.getProducers().getId())) {
                drug.setProducers(producers);
                sessionFactory.getCurrentSession().save(drug);
                result.add("success");
                Handler.drugs = getDrugsList();
                return result;
            }
        }
        result.add("failed");
        return result;
    }

    public List getDrugsList(){
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Drug> query = builder.createQuery(Drug.class);
        Root<Drug> root = query.from(Drug.class);
        query.select(root);
        Query<Drug> q = sessionFactory.getCurrentSession().createQuery(query);
        return q.getResultList();
    }

    public List getProducersList(){
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Producers> query = builder.createQuery(Producers.class);
        Root<Producers> root = query.from(Producers.class);
        query.select(root);
        Query<Producers> q = sessionFactory.getCurrentSession().createQuery(query);
        return q.getResultList();
    }

    public List updateDrug(String id, int value, String type){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug = (Drug) o;
            if (drug.getId().equals(id)) {
                if (type.equals("money")) drug.setMoney(value);
                else drug.setStock(value);
                sessionFactory.getCurrentSession().update(drug);
                Handler.drugs = getDrugsList();
                result.add("success");
                return result;
            }
        }
        result.add("failed");
        return result;
    }

    public List getNOP (String producerID){
        List<String> result = new ArrayList<>();
        int count = 0;
        for (Object o : Handler.drugs) {
            Drug drug = (Drug) o;
            if (drug.getProducers().getId().equals(producerID)) {
                count++;
            }
        }
        result.add(Integer.toString(count));
        return result;
    }

    public List getDrugsByGroup(String group){
        List<Drug> result = new ArrayList<>();
        for (int i = 0; i < Handler.drugs.size(); i++) {
            Drug drug = (Drug) Handler.drugs.get(i);
            if (drug.getDrugGroup().equals(group)) {
                result.add(drug);
            }
        }
        return result;
    }

    public List getDrugsByType(String type){
        List<Drug> result = new ArrayList<>();
        for (int i = 0; i < Handler.drugs.size(); i++) {
            Drug drug = (Drug) Handler.drugs.get(i);
            if (drug.getType().equals(type)) {
                result.add(drug);
            }
        }
        return result;
    }

    public List getDrugsByFilter(String group, String type, String sortType){
        List<Drug> result;
        if (!group.equals("none")){
            if (type.equals("none")){
                result = getDrugsByGroup(group);
            }
            else {
                result = new ArrayList<>();
                for (int i = 0; i < Handler.drugs.size(); i++) {
                    Drug drug = (Drug) Handler.drugs.get(i);
                    if (drug.getType().equals(type) && drug.getDrugGroup().equals(group)) {
                        result.add(drug);
                    }
                }
            }
        }
        else {
            if (!type.equals("none")) {
                result = getDrugsByType(type);
            }
            else result = getDrugsList();
        }
        switch (sortType) {
            case "money-asc":
                Collections.sort(result);
                break;
            case "money-des":
                Collections.sort(result);
                Collections.reverse(result);
                break;
            case "name-asc":
                result.sort(new Comparator<Drug>() {
                    public int compare(Drug drug1, Drug drug2) {
                        return drug1.getName().compareTo(drug2.getName());
                    }
                });
                break;
            case "name-des":
                result.sort(new Comparator<Drug>() {
                    public int compare(Drug drug1, Drug drug2) {
                        return drug1.getName().compareTo(drug2.getName());
                    }
                });
                Collections.reverse(result);
                break;
            default: break;
        }
        return result;
    }

    public List saveProducer(Producers newProducer){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.producers) {
            Producers producer = (Producers) o;
            if (producer.getId().equals(newProducer.getId())) {
                result.add("failed");
                return result;
            }
        }
        sessionFactory.getCurrentSession().save(newProducer);
        result.add("success");
        Handler.producers = getProducersList();
        return result;
    }

    public List deleteDrug(String id){
        List<String> result = new ArrayList<>();
        for (Object o : Handler.drugs) {
            Drug drug = (Drug) o;
            if (drug.getId().equals(id)) {
                sessionFactory.getCurrentSession().delete(drug);
                Handler.drugs = getDrugsList();
                result.add("success");
                return result;
            }
        }
        result.add("failed");
        return result;
    }
}
