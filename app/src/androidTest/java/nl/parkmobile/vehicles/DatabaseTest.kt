package nl.parkmobile.vehicles


import nl.parkmobile.vehicles.service.model.dao.VehiclesDao
import nl.parkmobile.vehicles.service.model.database.AppDatabase
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData
import androidx.annotation.Nullable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private var database: AppDatabase? = null
    private var vehiclesDao: VehiclesDao? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
            .allowMainThreadQueries().build()
        vehiclesDao = database?.vehiclesDao()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        database?.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndQueryVehicle() {
        val vehiclesContent = VehiclesData(1234, "car")
        vehiclesDao?.insertVehicle(vehiclesContent)
        val vehicleTest = getValue(vehiclesDao?.queryVehicles()!!)
        Assert.assertEquals(vehiclesContent.vehicleId, vehicleTest[0].vehicleId)
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(@Nullable o: T) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}
