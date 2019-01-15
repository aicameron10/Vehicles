package nl.parkmobile.vehicles.service.model.vehicles

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "vehicles")
@JsonClass(generateAdapter = true)
@Parcelize
data class VehiclesData(@PrimaryKey
                        @NonNull
                        var vehicleId: Long? = 0,
                        var vrn: String? = null,
                        var country: String? = null,
                        var color: String? = null,
                        var type: String? = null,
                        var default: Boolean? = false): Parcelable
