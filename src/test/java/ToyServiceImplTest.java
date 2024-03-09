//import mapping.dtos.ToyDTO;
//import model.Toy;
//import org.junit.Before;
//import org.junit.Test;
//import services.ToyStorelmpl;
//
//import java.util.*;
//
//import static org.junit.Assert.*;

//public class ToyServiceImplTest {
//
//    private ToyStorelmpl service;
//
//    @Before
//    public void setup() {
//        service = new ToyStorelmpl();
//    }
//
//    @Test
//    public void addToy_Successful() throws Exception {
//        String name = "Cat";
//        ToyType type = ToyType.MALE;
//        Integer price = 200000;
//        Integer amount = 4;
//        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount);
//        List<ToyDTO> expected = Collections.singletonList(toyToAdd);
//        List<ToyDTO> result = service.addToy(toyToAdd);
//        assertEquals(expected,result);
//    }
//    @Test
//    public void addToy_Exception() throws Exception {
//        String name = "Cat";
//        ToyType type = ToyType.MALE;
//        Integer price = 200000;
//        Integer amount = 3;
//        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount);
//        List<ToyDTO> expected = Collections.singletonList(toyToAdd);
//        List<ToyDTO> result = service.addToy(toyToAdd);
//        assertEquals(expected,result);
//        assertThrows(Exception.class,()->service.addToy(toyToAdd));
//    }
//
//    @Test
//    public void list_Successful(){
//        List<ToyDTO> toyStoreDTOList = service.listToys();
//        assertNotNull(toyStoreDTOList);
//        assertFalse(toyStoreDTOList.isEmpty());
//    }
//
//    @Test
//    public void search_Successful() throws Exception {
//        String name = "Cat";
//        ToyType type = ToyType.MALE;
//        Integer price = 200000;
//        Integer amount = 3;
//        ToyDTO toyExpected = new ToyDTO(name,type,price,amount);
//        ToyDTO result = service.search(name);
//        assertEquals(toyExpected,result);
//    }
//
//    @Test
//    public void search_Exception()  {
//        String name = "Catt";
//        assertThrows(Exception.class,()->service.search(name));
//    }
//    @Test
//    public void maxToy_Successful()  {
//        Map<ToyType, Integer> testMap = Map.of(ToyType.MALE,30,ToyType.FEMALE,10,ToyType.UNISEX,50);
//        Map.Entry<ToyType,Integer> expected = testMap.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
//        Map.Entry<ToyType,Integer> result = service.maxToy();
//        assertEquals(expected,result);
//
//    }
//
//    @Test
//    public void minToy_Successful()throws Exception{
//        Map<ToyType, Integer> testMap = Map.of(ToyType.MALE,30,ToyType.FEMALE,10,ToyType.UNISEX,50);
//        Map.Entry<ToyType,Integer> expected = testMap.entrySet().stream().min(Map.Entry.comparingByValue()).orElse(null);
//        Map.Entry<ToyType,Integer> result = service.minToy();
//        assertEquals(expected,result);
//
//    }
//
//    @Test
//    public void increase_Successful()throws Exception{
//        String name = "Cat";
//        ToyType Type = ToyType.MALE;
//        Integer price = 200000;
//        int amount = 3;
//        int newAmount = 4;
//        ToyDTO toyToAdd = new ToyDTO(name,Type,price,amount+newAmount);
//        List<ToyDTO> listExpected = Collections.singletonList(toyToAdd);
//        List<ToyDTO> result = service.increase(toyToAdd,newAmount);
//        assertEquals(listExpected,result);
//
//    }
//
//    @Test
//    public void increase_Exception(){
//        String name = "Cat";
//        ToyType type = ToyType.MALE;
//        Integer price = 200000;
//        int amount = 3;
//        int newAmount = 4;
//        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount+newAmount);
//        assertThrows(Exception.class,()->service.increase(toyToAdd,newAmount));
//
//    }
//    @Test
//    public void decrease_Successful() throws Exception{
//        String name = "Cat";
//        ToyType type = ToyType.MALE;
//        Integer price = 200000;
//        int amount = 4;
//        int newAmount = 1;
//        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount-newAmount);
//        List<ToyDTO> listExpected = Collections.singletonList(toyToAdd);
//        List<ToyDTO> result = service.decrease(toyToAdd,newAmount);
//        assertEquals(listExpected,result);
//
//    }
//    @Test
//    public void showByType_Successful()throws Exception{
//        Map<ToyType,Integer> mapExpected = new TreeMap<>();
//        mapExpected.put(ToyType.MALE,1);
//        Map<ToyType,Integer> result = service.showByType();
//        assertEquals(mapExpected,result);
//    }
//
//    @Test
//    public void showByType_Exception()throws Exception{
//        service.setToyStore(null);
//        assertThrows(Exception.class,()->service.showByType());
//    }
//
//    @Test
//    public void sort_Successful() throws Exception {
//        Map<ToyType,Integer> unsortedMap = new HashMap<>();
//        unsortedMap.put(ToyType.FEMALE,3);
//        unsortedMap.put(ToyType.MALE,1);
//        unsortedMap.put(ToyType.UNISEX,2);
//        Map<ToyType,Integer> expectedMap = new HashMap<>();
//        expectedMap.put(ToyType.MALE,1);
//        expectedMap.put(ToyType.UNISEX, 2);
//        expectedMap.put(ToyType.FEMALE,3);
//        Map<ToyType,Integer> result = service.sort();
//        assertEquals(expectedMap,result);
//    }
//
//    @Test
//    public void sort_Exception()  {
//        Map<ToyType,Integer> unsortedMap = new HashMap<>();
//        unsortedMap.put(ToyType.FEMALE,3);
//        unsortedMap.put(ToyType.MALE,1);
//        unsortedMap.put(ToyType.UNISEX,2);
//        Map<ToyType,Integer> expectedMap = new HashMap<>();
//        expectedMap.put(ToyType.MALE,1);
//        expectedMap.put(ToyType.UNISEX,2);
//        expectedMap.put(ToyType.FEMALE,3);
//
//        assertThrows(Exception.class,()->service.sort());
//    }
//
//    @Test
//    public void showToysAbove_Successful() throws Exception {
//        List<Toy> expectedList = new ArrayList<>();
//        expectedList.add(new Toy("cow",8,100,ToyType.UNISEX));
//        expectedList.add(new Toy("horse",9,150,ToyType.UNISEX));
//        service.setToyStore(expectedList);
//        int value = 100;
//        List<ToyDTO> result = service.showToysAbove(value);
//        for (ToyDTO toyDTO : result) {
//            assertTrue(toyDTO.price() >= value);
//        }
//    }
//
//    @Test
//    public void showToysAbove_Exception() throws Exception {
//        List<Toy> expectedList = new ArrayList<>();
//        service.setToyStore(expectedList);
//        int value = 100;
//        assertThrows(Exception.class,()->service.showToysAbove(value));
//    }
//
//    @Test
//    public void verifyExist_True(){
//        ToyDTO toyStoreDTO = new ToyDTO("rex",ToyType.UNISEX,1200,10);
//        assertTrue(service.verifyExist(toyStoreDTO.name()));
//    }
//    @Test
//    public void verifyExist_False(){
//        assertFalse(service.verifyExist("coww"));
//    }
//
//    @Test
//    public void totalToys_Successful() throws Exception {
//        service.addToy(new ToyDTO("chicken",ToyType.UNISEX,1200,10));
//        service.addToy(new ToyDTO("bike",ToyType.MALE,2000,8));
//        int expectedTotal = 10+8;
//        int result = service.totalToys();
//        assertEquals(expectedTotal,result);
//    }
//
//    @Test
//    public void totalPrices_Successful() throws Exception{
//        service.addToy(new ToyDTO("doll",ToyType.UNISEX,1200,10));
//        service.addToy(new ToyDTO("car",ToyType.MALE,2000,8));
//        int expectedTotal = 1200+2000;
//        int result = service.totalPriceAllToys();
//        assertEquals(expectedTotal,result);
//    }
//}


