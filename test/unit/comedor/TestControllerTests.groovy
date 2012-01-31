package comedor



import org.junit.*
import grails.test.mixin.*

@TestFor(TestController)
@Mock(Test)
class TestControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/test/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.testInstanceList.size() == 0
        assert model.testInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.testInstance != null
    }

    void testSave() {
        controller.save()

        assert model.testInstance != null
        assert view == '/test/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/test/show/1'
        assert controller.flash.message != null
        assert Test.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/test/list'


        populateValidParams(params)
        def test = new Test(params)

        assert test.save() != null

        params.id = test.id

        def model = controller.show()

        assert model.testInstance == test
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/test/list'


        populateValidParams(params)
        def test = new Test(params)

        assert test.save() != null

        params.id = test.id

        def model = controller.edit()

        assert model.testInstance == test
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/test/list'

        response.reset()


        populateValidParams(params)
        def test = new Test(params)

        assert test.save() != null

        // test invalid parameters in update
        params.id = test.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/test/edit"
        assert model.testInstance != null

        test.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/test/show/$test.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        test.clearErrors()

        populateValidParams(params)
        params.id = test.id
        params.version = -1
        controller.update()

        assert view == "/test/edit"
        assert model.testInstance != null
        assert model.testInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/test/list'

        response.reset()

        populateValidParams(params)
        def test = new Test(params)

        assert test.save() != null
        assert Test.count() == 1

        params.id = test.id

        controller.delete()

        assert Test.count() == 0
        assert Test.get(test.id) == null
        assert response.redirectedUrl == '/test/list'
    }
}
