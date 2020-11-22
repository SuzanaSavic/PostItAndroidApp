package suzanasavic.github.com.android.postitapp.data.entities


import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import suzanasavic.github.com.android.postitapp.Constants.Companion.BODY
import suzanasavic.github.com.android.postitapp.Constants.Companion.ID
import suzanasavic.github.com.android.postitapp.Constants.Companion.TABLE_NAME
import suzanasavic.github.com.android.postitapp.Constants.Companion.TITLE
import suzanasavic.github.com.android.postitapp.Constants.Companion.USER_ID
import java.io.Serializable

@Dao
@Entity(tableName = TABLE_NAME)
data class Post(
    @ColumnInfo(name = BODY)
    @SerializedName(BODY)
    val body: String,
    @PrimaryKey
    @ColumnInfo(name = ID)
    @SerializedName(ID)
    val id: Int,
    @ColumnInfo(name = TITLE)
    @SerializedName(TITLE)
    val title: String,
    @ColumnInfo(name = USER_ID)
    @SerializedName(USER_ID)
    val userId: Int
) : Serializable