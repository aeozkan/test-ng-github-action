import org.testng.annotations.*;

public class MyTests {
    @BeforeSuite
    public void BSuit(){
        System.out.println("BeforeSuite");
    }
    @BeforeTest
    public void BTest(){
        System.out.println("BeforeTest");
    }
    @BeforeClass
    public void BClass(){
        System.out.println("BeforeClass");
    }
    @BeforeGroups
    public void BGroup(){
        System.out.println("BeforeGroups");
    }
    @BeforeMethod
    public void BMethod(){
        System.out.println("BeforeMethod");
    }
    @Test(priority = 2, timeOut = 1000)
    public void test01(){
        System.out.println("test 01");
    }
    @Test(enabled = false, description = "bu test iptal")
    public void test02(){
        System.out.println("test 02");
    }
    @Test(priority = 3, invocationCount = 3)
    public void test03(){
        System.out.println("test 03");
    }
    @Test(priority = 1, dependsOnMethods = "test03", invocationCount = 2)
    public void test04(){
        System.out.println("test 04");
    }
    @AfterMethod
    public void AMethod(){
        System.out.println("AfterMethod");
    }
    @AfterGroups
    public void AGroup(){
        System.out.println("AfterGroups");
    }
    @AfterClass
    public void AClass(){
        System.out.println("AfterClass");
    }
    @AfterTest
    public void ATest(){
        System.out.println("AfterTest");
    }
    @AfterSuite
    public void ASuit(){
        System.out.println("AfterSuite");
    }
}
