package com.example.yummy


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
import kotlinx.android.synthetic.main.fragment_manage_acount_information.*
import java.net.URL
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.HttpURLConnection


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class manageAcountInformation : Fragment() {
    var find=true

    var mAuth: String = " "
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_acount_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance().currentUser!!.uid

      // load()
       /* setting.setOnClickListener {
            view!!.findNavController().navigate(R.id.editInformation)
        }*/
        logout.setOnClickListener {
            view!!.findNavController().navigate(R.id.loginAcount)
        }

       // logoimag.setOnClickListener {
            //for imgae
        //}


    }


    private fun load() {
      //  user()
        //userInfo()
    // logo()
       // var uRL="https://firebasestorage.googleapis.com/v0/b/yummy-b55d9.appspot.com/o/images%2FTJSmWa0DNfQbFeDDGyLaAkxasJt2%2Flogo.jpg?alt=media&token=3991487e-0856-4fc7-a69a-8288bfc0a94b\""
        var url: String ="https://firebasestorage.googleapis.com/v0/b/yummy-b55d9.appspot.com/o/images%2FTJSmWa0DNfQbFeDDGyLaAkxasJt2%2Fcoffeeday.jpg?alt=media&token=c34a3923-b93c-4815-880b-637a4ed12a71"
var new=url as URL
        var http=url.openConnection() as HttpURLConnection
//http.connectTimeout=700
        http.doInput
        http.connect()

        var inputStream=http.inputStream

        //BufferedInputStream(http.getInputStream())
        val image = BitmapFactory.decodeStream(inputStream)
        idlogin.setImageBitmap(image)

    }

    private fun user() {
        var database = FirebaseDatabase.getInstance().getReference("User")
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    if(find){
                    for (e in p0.children) {

                        var user = e.getValue(User::class.java) as User
//change
                        if (user!!.id.equals(mAuth)) {
                            nameRES.text = user!!.username
                            find=false
                            var t=e.key
                           /* remove
                           var firebaseDel=FirebaseDatabase.getInstance().reference.child("User").child(t.toString())
                            firebaseDel.removeValue()*/



                        }
                    }}
                }

            }
        })
    }


    private fun userInfo() {
        var database = FirebaseDatabase.getInstance().getReference("UserInfo")
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {

                    for (e in p0.children) {
                        var userinfo = e.getValue(UserInfo::class.java) as UserInfo


                        if (userinfo!!.id.equals(mAuth)) {
                            var uRL = userinfo.logUrl.trim()
                            //MyAsyncTask().execute(uRL)

                            var open = userinfo!!.timeOpen.toString()
                            var close = userinfo!!.timeClose.toString()
                            timeclose.text = open + "-" + close+" AM"
                            var deilfrom = userinfo!!.deliFrom.toString()
                            var deilto = userinfo!!.deliTo.toString()
                            timeDelivery.text = deilfrom + "-" + deilto + " min"
                            moneny.text = userinfo!!.cost.toString() + " SAR"


                        }
                    }
                }

            }
        })
    }

    private fun logo() {


        /*
        var database = FirebaseDatabase.getInstance().getReference("UserInfo")
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {


                        for (e in p0.children) {
                            var user = e.getValue(UserInfo::class.java) as UserInfo
//change
                            if (user!!.id.equals(mAuth)) {
                                var uRL = user.logUrl
                                MyAsyncTask().execute(uRL)





                            }
                            else
                            {
                                Toast.makeText(context, "not enter", Toast.LENGTH_SHORT).show()

                            }
                        }

                }
            }
        })
    }

*/

        //MyAsyncTask().execute("https://firebasestorage.googleapis.com/v0/b/yummy-b55d9.appspot.com/o/images%2FTJSmWa0DNfQbFeDDGyLaAkxasJt2%2Fcoffeeday.jpg?alt=media&token=c34a3923-b93c-4815-880b-637a4ed12a71")

    }

 /*   inner class MyAsyncTask:AsyncTask<String,String,String>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg params: String?): String {
            try{
              var url=URL(params[0])
                var http=url.openConnection() as HttpURLConnection
//http.connectTimeout=700
                http.doInput
                http.connect()
                var url:URL="https://firebasestorage.googleapis.com/v0/b/yummy-b55d9.appspot.com/o/images%2FTJSmWa0DNfQbFeDDGyLaAkxasJt2%2Fcoffeeday.jpg?alt=media&token=c34a3923-b93c-4815-880b-637a4ed12a71

                var inputStream=http.inputStream

                        //BufferedInputStream(http.getInputStream())
                val image = BitmapFactory.decodeStream(inputStream)
                idlogin.setImageBitmap(image)



            }
            catch (e:Exception){
                //Toast.makeText(context,"Failure  "+e.message,Toast.LENGTH_LONG).show()

            }
            return ""
        }

    }*/

    }
