package caribehostal.caseroserver.dataaccess

import java.io.File

import android.os.Environment.getExternalStorageDirectory

/**
 * @author rainermf
 */

object Resources {
    val directory = File(getExternalStorageDirectory().absolutePath + "/Casero")

    fun resolve(filename: String) = directory.resolve(filename)
}
