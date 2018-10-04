package com.example.yummy


import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.fragment_edit_information.*
import kotlinx.android.synthetic.main.fragment_manage_acount_information.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class editInformation : Fragment() {

    var mAuth: String = " "
    private var filePath: Uri? = null
    internal var storage: FirebaseStorage? = null
    internal var storageRef: StorageReference? = null
    private val REQUEST_PICK_IMAGE = 1234

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance().currentUser!!.uid

        addphoto.setOnClickListener {
            //loadImage()
            loadImg()

        }
        ok.setOnClickListener {
            if (!(nameedit.text.isEmpty())) {
                editUsername()
            }
            if (!(openedit.text.isEmpty() || closeedit.text.isEmpty() || toedit.text.isEmpty() || fromedit.text.isEmpty() || fromedit.text.isEmpty())) {
                editUsernameInfo()
            }
            view!!.findNavController().navigate(R.id.manageAcountInformation)

        }
        Canccel.setOnClickListener {
            view!!.findNavController().navigate(R.id.manageAcountInformation)

        }
    }

    /*
private fun loadImage(){
    val userID="1SRFDYO"//change
    val phpto=1
        val storage=FirebaseStorage.getInstance()
    val storageLink=storage.getReferenceFromUrl("gs://yummy-b55d9.appspot.com")
    val format =SimpleDateFormat("ddMMyyHHmmss")
    val date=Date()
    val imgaePath=userID+"."+format.format(date)+"jpg"
    val imageRes=storageLink.child("userID").child("Res/"+imgaePath)

    /*var bitmapphor=phpto as BitmapDrawable
   bitmapphor=bitmapphor.Bitmap*/

    }*/
    private fun loadImg() {
        storage = FirebaseStorage.getInstance()
        storageRef = storage!!.reference
        select()


    }

    private fun select() {
        var PICK_IMAGE_REQUEST = 1234
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), REQUEST_PICK_IMAGE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data;
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver, filePath)
               // imageprofile.setImageBitmap(bitmap)
             //   upload()
                // imageView11!!.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
/*
    private fun upload() {
        var find=true
        val mAuth = FirebaseAuth.getInstance().currentUser!!.uid

        if (filePath != null) {
            val possDia = ProgressDialog(context)
            possDia.setTitle("Uploading....")
            possDia.show()
            //edit here
            val imageRers = storageRef!!.child("images/" + mAuth + "/logo.jpg")
            imageRers.putFile(filePath!!).addOnSuccessListener {
                 var UrlLogo=it.uploadSessionUri.toString().trim()


                var database = FirebaseDatabase.getInstance().getReference("Image")
                database.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0!!.exists()) {
                            if (find) {
                                for (e in p0.children) {
                                    var user = e.getValue(image::class.java) as image
//change
                                    if (user!!.id.equals(mAuth)) {
                                        find = false
                                        var t = e.key

                                        var firebaseDel = FirebaseDatabase.getInstance().reference.child("Image").child(t.toString())
                                        firebaseDel.removeValue()
                                        //insert
                                        user.PhotoUrl = UrlLogo
                                        val userkey = database.push().key
                                        database.child(userkey!!).setValue(user).addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(context, "Edit Successful ", Toast.LENGTH_LONG).show()


                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                })








                possDia.dismiss()
                Toast.makeText(context, "Image Change sussful", Toast.LENGTH_LONG).show()

            }
                    .addOnFailureListener {
                        possDia.dismiss()
                        Toast.makeText(context, "Image Change Not Sucssful", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        possDia.setMessage("Change " + progress.toInt() + "%...")
                    }

        }
    }
*/
    private fun editUsername() {
        var find = true


        var database = FirebaseDatabase.getInstance().getReference("User")
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
            }
           // mAuth="WNlt9902oqYyQxNdfR6CB62YDQS2"

            override fun onDataChange(p0: DataSnapshot) {


                    if (p0!!.exists()) {
                        for (e in p0.children) {
                            var user = e.getValue(User::class.java) as User
//change
                            if (user!!.id.equals(mAuth)) {
                                find = false

                                var t = e.key
                                var newname=nameedit.text.toString()
                                if(newname.isEmpty())
                                {
                                    Toast.makeText(context, "is Empty", Toast.LENGTH_LONG).show()

                                }
                                else{
                                //insert
                                var newuser=User(newname,user.email,user.phone,user.type,user.id)
newname=""
                                var firebaseDel = FirebaseDatabase.getInstance().reference.child("User").child(t.toString())
                                firebaseDel.removeValue()
                              //  user.username = nameedit.text.toString()
                                val userkey = database.push().key
                                database.child(userkey!!).setValue(newuser).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {

                                        Toast.makeText(context, "Edit Successful ", Toast.LENGTH_LONG).show()


                                    }
                                }
                            }
                        }
                    }
                }}

        })

    }
    private fun editUsernameInfo(){



        var find = true


        var database = FirebaseDatabase.getInstance().getReference("UserInfo")
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    if (find) {
                        for (e in p0.children) {
                            var user = e.getValue(UserInfo::class.java) as UserInfo
//change
                            if (user!!.id.equals(mAuth)) {
                                find = false
                                var t = e.key

                                var firebaseDel = FirebaseDatabase.getInstance().reference.child("UserInfo").child(t.toString())
                                firebaseDel.removeValue()
                                //insert
                                if(!(openedit.text.isEmpty()))
                                user.timeOpen = nameedit.text.toString().toInt()
                                if(!(closeedit.text.isEmpty()))
                                    user.timeClose = nameedit.text.toString().toInt()
                                if(!(toedit.text.isEmpty()))
                                    user.deliFrom = nameedit.text.toString().toInt()
                                if(!(fromedit.text.isEmpty()))
                                    user.deliTo = nameedit.text.toString().toInt()
                                if(!(moneny.text.isEmpty()))
                                    user.cost = nameedit.text.toString().toInt()


                                val userkey = database.push().key
                                database.child(userkey!!).setValue(user).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "Edit Successful ", Toast.LENGTH_LONG).show()


                                    }
                                }
                            }
                        }
                    }
                }
            }
        })






    }
}
