
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {
    public Phones book;
    @Before
    public void setUp(){

        book = new Phones();
    }
    @Test
    public void add1(){
        book.add("Bug#", "bug", "ggg", "A", "open");
        int actual = book.size();
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void delete1(){
        book.delete("Bug#");
        int actual = book.size();
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void size1(){
        book.add("Bug#", "bug", "ggg", "A", "open");
        book.add("Bug1", "bug", "ggg", "A", "open");
        int actual = book.size();
        int expected = 2;
        Assert.assertEquals(expected, actual);
    }
}
