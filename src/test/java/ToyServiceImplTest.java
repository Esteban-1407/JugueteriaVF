import mapping.dtos.ToyDTO;
import model.ToyType;
import org.junit.Before;
import org.junit.Test;
import services.ToyStorelmpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ToyServiceImplTest {

    private ToyStorelmpl service;

    @Before
    public void setup() {
        service = new ToyStorelmpl();
    }

    @Test
    public void addToy_Successful() throws Exception {
        String name = "Cat";
        ToyType type = ToyType.MALE;
        Integer price = 200000;
        Integer amount = 4;
        ToyDTO toyToAdd = new ToyDTO(name, type, price, amount);
        List<ToyDTO> expected = Collections.singletonList(toyToAdd);
        List<ToyDTO> result = service.addToy(toyToAdd);
        assertEquals(expected, result);
    }

    @Test(expected = Exception.class)
    public void addToy_Exception() throws Exception {
        String name = "Cat";
        ToyType type = ToyType.MALE;
        Integer price = 200000;
        Integer amount = 3;
        ToyDTO toyToAdd = new ToyDTO(name, type, price, amount);
        List<ToyDTO> expected = Collections.singletonList(toyToAdd);
        List<ToyDTO> result = service.addToy(toyToAdd);
        assertEquals(expected, result);
        service.addToy(toyToAdd);
    }

    @Test
    public void listToys_Successful() {
        List<ToyDTO> toyStoreDTOList = service.listToys();
        assertNotNull(toyStoreDTOList);
        assertFalse(toyStoreDTOList.isEmpty());
    }

    @Test
    public void search_Successful() throws Exception {
        String name = "Cat";
        ToyType type = ToyType.MALE;
        Integer price = 200000;
        Integer amount = 3;
        ToyDTO toyExpected = new ToyDTO(name, type, price, amount);
        ToyDTO result = service.search(name);
        assertEquals(toyExpected, result);
    }

    @Test(expected = Exception.class)
    public void search_Exception() throws Exception {
        String name = "Catt";
        service.search(name);
    }

    @Test
    public void maxToy_Successful() {
        Map.Entry<ToyType, Integer> result = service.maxToy();
        assertNotNull(result);
    }

    @Test
    public void minToy_Successful() {
        Map.Entry<ToyType, Integer> result = service.minToy();
        assertNotNull(result);
    }

    @Test
    public void increase_Successful() throws Exception {
        String name = "Cat";
        ToyType type = ToyType.MALE;
        Integer price = 200000;
        int amount = 3;
        int newAmount = 4;
        ToyDTO toyToAdd = new ToyDTO(name, type, price, amount + newAmount);
        List<ToyDTO> listExpected = Collections.singletonList(toyToAdd);
        List<ToyDTO> result = service.increase(toyToAdd, newAmount);
        assertEquals(listExpected, result);
    }

    @Test(expected = Exception.class)
    public void increase_Exception() throws Exception {
        String name = "Cat";
        ToyType type = ToyType.MALE;
        Integer price = 200000;
        int amount = 3;
        int newAmount = 4;
        ToyDTO toyToAdd = new ToyDTO(name, type, price, amount + newAmount);
        service.increase(toyToAdd, newAmount);
    }

    @Test
    public void decrease_Successful() throws Exception {
        String name = "Cat";
        ToyType type = ToyType.MALE;
        Integer price = 200000;
        int amount = 4;
        int newAmount = 1;
        ToyDTO toyToAdd = new ToyDTO(name, type, price, amount - newAmount);
        List<ToyDTO> listExpected = Collections.singletonList(toyToAdd);
        List<ToyDTO> result = service.decrease(toyToAdd, newAmount);
        assertEquals(listExpected, result);
    }

    @Test
    public void showByType_Successful() {
        Map<ToyType, Integer> result = service.showByType();
        assertNotNull(result);
    }

    @Test(expected = Exception.class)
    public void showByType_Exception() throws Exception {
        service.setToyStore(null);
        service.showByType();
    }

    @Test
    public void sort_Successful() {
        Map<ToyType, Integer> result = service.sort();
        assertNotNull(result);
    }

    @Test(expected = Exception.class)
    public void sort_Exception() throws Exception {
        service.setToyStore(null);
        service.sort();
    }

    @Test
    public void showToysAbove_Successful() throws Exception {
        List<ToyDTO> result = service.showToysAbove(100);
        assertNotNull(result);
    }

    @Test(expected = Exception.class)
    public void showToysAbove_Exception() throws Exception {
        service.setToyStore(null);
        service.showToysAbove(100);
    }

    @Test
    public void verifyExist_True() {
        ToyDTO toyStoreDTO = new ToyDTO("rex", ToyType.UNISEX, 1200, 10);
        assertTrue(service.verifyExist(toyStoreDTO.name()));
    }

    @Test
    public void verifyExist_False() {
        assertFalse(service.verifyExist("coww"));
    }

    @Test
    public void totalToys_Successful() throws Exception {
        service.addToy(new ToyDTO("chicken", ToyType.UNISEX, 1200, 10));
        service.addToy(new ToyDTO("bike", ToyType.MALE, 2000, 50));
    }
}
