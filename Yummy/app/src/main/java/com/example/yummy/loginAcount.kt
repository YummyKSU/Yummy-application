package com.example.yummy


import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_login_acount.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class loginAcount : Fragment() {
    lateinit var username:String
    lateinit var password:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_acount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var masadDilog = ProgressDialog(context)
        masadDilog.setMessage("Checking...")
        sginup.setOnClickListener {
            masadDilog.show()

             username = oldemail.text.toString()
             password = oldpasw.text.toString()
            if (username.isEmpty() || password.isEmpty()) {

                masadDilog.dismiss()



                Toast.makeText(context, "Enter the vailds", Toast.LENGTH_LONG).show()
            } else {
          check(username, password,masadDilog)


            }


        }
        signuppage.setOnClickListener{
            view!!.findNavController().navigate(R.id.createacount)

        }
    }

    //add type
    //chage bar
    //add dilog prossgaer
    private fun check(W: String, P: String,masadDilog:ProgressDialog) {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(W, P).addOnCompleteListener {task ->
            var  mAuth = FirebaseAuth.getInstance().currentUser!!.uid

            if (task.isSuccessful) {

              var  mAuth = FirebaseAuth.getInstance().currentUser!!.uid


                var database = FirebaseDatabase.getInstance().getReference("User")
                database.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0!!.exists()) {

                            for (e in p0.children) {

                                var user = e.getValue(User::class.java) as User
//change
                                if (user!!.id.equals(mAuth)) {
                                    var type=user.type.toUpperCase()
                                    if((type.equals("FOOD PROVIDER"))){
                                        view!!.findNavController().navigate(R.id.action_loginAcount_to_manageAcountInformation)
                                        masadDilog.dismiss()

                                    }



else{
                                       // new page
                                        masadDilog.dismiss()

                                    view!!.findNavController().navigate(R.id.displayList)}









                                }
                            }

                        }
                    }

                })
            }
            else
            {
                masadDilog.dismiss()
                Toast.makeText(context, ""+task.exception, Toast.LENGTH_LONG).show()

            }
        }
    }
}
