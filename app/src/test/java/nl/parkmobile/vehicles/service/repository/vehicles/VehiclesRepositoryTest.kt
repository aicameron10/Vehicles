package nl.parkmobile.vehicles.service.repository.vehicles

import junit.framework.TestCase
import nl.parkmobile.vehicles.InstantTaskExtension
import nl.parkmobile.vehicles.service.model.dao.VehiclesDao
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback


@ExtendWith(InstantTaskExtension::class)
internal class VehiclesRepositoryTest {

    //repository + service
    @InjectMocks
    private lateinit var vehiclesRepository: VehiclesRepository
    @Mock
    private lateinit var mockVehicleService: VehiclesService
    @Mock
    private lateinit var vehiclesDao: VehiclesDao
    @Mock
    private lateinit var mockResponseVehiclesResponse: retrofit2.Response<VehiclesResponse>
    @Mock
    private lateinit var mockVoucherCall: Call<VehiclesResponse>

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Nested
    inner class GetVehicles {
        @Test
        fun `vehicles liveData is null if network call not successful`() {
            //Setup the mocks
            Mockito.`when`(mockVehicleService.getVehicles()).thenReturn(mockVoucherCall)
            Mockito.`when`(mockResponseVehiclesResponse.isSuccessful).thenReturn(false)
            Mockito.`when`(mockResponseVehiclesResponse.raw()).thenReturn(Mockito.mock(okhttp3.Response::class.java))

            //Invoke the on success callback when retrofit's enqueue method called for vehicles
            Mockito.doAnswer { invocation ->
                val callback: Callback<VehiclesResponse> = invocation.getArgument(0)
                callback.onResponse(mockVoucherCall, mockResponseVehiclesResponse)
                callback
            }.`when`(mockVoucherCall).enqueue(ArgumentMatchers.any())

            //Make request to repository to get vehicles
            val liveData = vehiclesRepository.getVehicles()

            TestCase.assertTrue(liveData.value == null)
        }

        @Test
        fun `vehicles liveData is populated if network call successful`() {
            //Setup the mocks
            val mockVoucherResponse = Mockito.mock(VehiclesResponse::class.java)
            Mockito.`when`(mockVehicleService.getVehicles()).thenReturn(mockVoucherCall)
            Mockito.`when`(mockResponseVehiclesResponse.isSuccessful).thenReturn(true)
            Mockito.`when`(mockResponseVehiclesResponse.body()).thenReturn(mockVoucherResponse)
            Mockito.`when`(mockResponseVehiclesResponse.raw()).thenReturn(Mockito.mock(okhttp3.Response::class.java))

            //Invoke the on success callback when retrofit's enqueue method called for vehicles
            Mockito.doAnswer { invocation ->
                val callback: Callback<VehiclesResponse> = invocation.getArgument(0)
                callback.onResponse(mockVoucherCall, mockResponseVehiclesResponse)
                callback
            }.`when`(mockVoucherCall).enqueue(ArgumentMatchers.any())

            //Make request to repository to get vehicles
            val liveData = vehiclesRepository.getVehicles()

            TestCase.assertTrue(liveData.value == mockVoucherResponse)
        }

        @Test
        fun `vehicles liveData is null if network call failed`() {
            //Setup the mocks
            Mockito.`when`(mockVehicleService.getVehicles()).thenReturn(mockVoucherCall)

            //Invoke the on failure callback when retrofit's enqueue method called for vehicles
            Mockito.doAnswer { invocation ->
                val callback: Callback<VehiclesResponse> = invocation.getArgument(0)
                callback.onFailure(mockVoucherCall, Mockito.mock(Throwable::class.java))
                callback
            }.`when`(mockVoucherCall).enqueue(ArgumentMatchers.any())

            //Make request to repository to get vehicles
            val liveData = vehiclesRepository.getVehicles()

            TestCase.assertTrue(liveData.value == null)
        }
    }
}