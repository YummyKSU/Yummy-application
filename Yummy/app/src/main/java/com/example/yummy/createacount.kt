package com.example.yummy


import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.RadioButton
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_createacount.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class createacount : Fragment() {
    lateinit var username:String
    lateinit var password:String
    lateinit var email:String
    lateinit var typeOFUser:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_createacount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          var masadDilog = ProgressDialog(context)


        masadDilog.setMessage("Checking...")
        sginup.setOnClickListener{

masadDilog.show()
            username = newemil.text.toString().trim()//unique
            password = newpass.text.toString().trim()
            var passwordsame = usernamec.text.toString().trim()
            email = number.text.toString().trim()





        }
        loginpage.setOnClickListener {
            view.findNavController().navigate(R.id.loginAcount)
        }
    }

    public   fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioButton ->
                    if (checked) {
                        typeOFUser="Customer"
                    }
                R.id.radioButton2 ->
                    if (checked) {
                        typeOFUser="Food delivery"
                    }
            }
        }
    }


    private fun addUser(email: String, username: String, number:String, type: String,masadDilog: ProgressDialog) {
        val mAuth = FirebaseAuth.getInstance()

//progressBar.setVisibility(view.VISIBLE)ADD TO SCREN
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //Toast.makeText(context, mAuth.uid, Toast.LENGTH_SHORT).show()
                val userId = mAuth.uid
                val database = FirebaseDatabase.getInstance().getReference("User")
                val userkey = database.push().key
                val user = User(username,email,number,type,userId!!)
                database.child(userkey!!).setValue(user).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var same = type.toUpperCase()

                        if (same.equals("FOOD PROVIDER")) {
                            val database1 = FirebaseDatabase.getInstance().getReference("UserInfo")
                            val userkey1 = database.push().key
                            val userinfo = UserInfo(0, username, 0, 0, 0, 0, userId!!, "")
                            database1.child(userkey1!!).setValue(userinfo).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    masadDilog.dismiss()

                                    Toast.makeText(context, " Create isSuccessful", Toast.LENGTH_SHORT).show()

                                    view!!.findNavController().navigate(R.id.loginAcount)

                                } else {
                                    masadDilog.dismiss()

                                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                                }
                            }

                        } else {
                            Toast.makeText(context, " Create isSuccessful", Toast.LENGTH_SHORT).show()
                            masadDilog.dismiss()
                            view!!.findNavController().navigate(R.id.loginAcount)

                        }
                    }
                    else{
masadDilog.dismiss()
                        Toast.makeText(context, "  email is used", Toast.LENGTH_SHORT).show()

                    }
        }


    }

            else{
                masadDilog.dismiss()

                Toast.makeText(context, "  email is used", Toast.LENGTH_SHORT).show()

            }
    }
    }

}
