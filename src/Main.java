import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("src/CarInfo");

        Scanner input = null;
        List<Car> list = new ArrayList<>();
        //VIN, counter insurances
        Map<String, Integer> insCars = new HashMap<>();
        //VIN, owners
        Map<String, Set<String>> vinOwners = new HashMap<>();

        Map<String, Car> insRegNo = new HashMap<>();
        try {
            input = new Scanner(file);

            while (input.hasNext()) {
                String[] car1 = input.nextLine().split(" ");
                String vin = car1[0];
//                String regNumber = input.next();
//                int year = input.nextInt();
//                String dateOfInsurance = input.next();
//                String ownerFirstName = input.next();
//                String ownerLastName = input.next();
                String owner = car1[5] + car1[6];
                Car car = new Car(car1[0], car1[1], Integer.parseInt(car1[2]), car1[3], car1[4], car1[5] + car1[6]);
                //add to list
                list.add(car);
                if (vinOwners.get(vin) == null) {
                    vinOwners.put(vin, new HashSet<>());
                }
                vinOwners.get(vin).add(owner);

                if (insCars.containsKey(vin)) {
                    insCars.put(vin, insCars.get(vin) + 1);
                } else {
                    insCars.put(vin, 1);
                }

                if (!insRegNo.containsKey(vin) || (insRegNo.containsKey(vin) && compareDates(car.getDateOfInsurance(), insRegNo.get(vin).getDateOfInsurance()) > 0)) {
                    insRegNo.put(vin, car);
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } finally {
            input.close();
        }


//        2. сортиране възходящо по бр. собств

        List<String> sortedVinOwn = new ArrayList<>(vinOwners.keySet().stream().toList());
          sortedVinOwn.sort((e1, e2) -> compareEntitiesOwn(e1, e2, vinOwners));


        List<String> sortInsNum = new ArrayList<>(insCars.keySet().stream().toList());
        sortInsNum.sort((ins1,ins2) -> compareIns(ins1,ins2,insCars));
        List<String> topFive = sortInsNum.subList(0, 5);



        File file2 = new File("src/Answer.txt");
//        if (file2.exists()) {
//            System.out.println("File already exists");
//            System.exit(1);
//        }
        PrintWriter output = null;
        try {
            output = new PrintWriter(file2);

            for(String vinNum : sortedVinOwn){
                if(topFive.contains(vinNum)){
                    output.println(insRegNo.get(vinNum).getRegNumber() + " " + vinOwners.get(vinNum).size() + " Owners " + insCars.get(vinNum) + " Ins");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the file
            output.close();
        }


    }

    private static int compareDates(String date1, String date2) {
        String[] d1 = date1.split("\\.");
        String[] d2 = date2.split("\\.");
        return (d1[2]).compareTo(d2[2]);
    }

    private static int compareEntitiesOwn(String e1, String e2, Map<String, Set<String>> map){
        return Integer.compare(map.get(e2).size(), map.get(e1).size());
    }

    private static int compareIns(String ins1, String ins2, Map<String, Integer> map){
        return Integer.compare(map.get(ins2), map.get(ins1));
    }
}
