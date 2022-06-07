package com.example.lojavirtual

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

@Suppress("DEPRECATION")
class UserProfileActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var imageProfile: ImageView
    lateinit var photoURI: Uri
    val REQUEST_TAKE_PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.user_perfil)

        imageProfile = findViewById(R.id.iv_profile_user)
        imageProfile.setOnClickListener { takePickture() }

        val profileImage = PreferenceManager.getDefaultSharedPreferences(this).getString(MediaStore.EXTRA_OUTPUT,null)
        if (profileImage != null){
            photoURI = Uri.parse(profileImage)
            imageProfile.setImageURI(photoURI)
        }else{
            imageProfile.setImageResource(R.drawable.profile_image)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
    @Throws(IOException::class)
    private fun createImageFile(): File{
        val timeStamp : String = SimpleDateFormat("yyyyMMdd HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("PROFILE ${timeStamp}",".jpg",storageDir)
    }
    //Metodo para tirar a foto
    private fun takePickture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                     createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        this,
                        "com.example.lojavirtual.fileprovider", it
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(intent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data )
    PreferenceManager.getDefaultSharedPreferences(this).apply {
        edit().putString(MediaStore.EXTRA_OUTPUT,photoURI.toString()).apply()
    }
        imageProfile.setImageURI(photoURI)
        }
    }