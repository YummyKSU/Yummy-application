package com.example.yummy


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_display_list.*
import kotlinx.android.synthetic.main.listfoodprivder.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class displayList : Fragment() {
    var ok:Boolean = false
    lateinit var mAuth: String
    var myadapter:MsgAdapter?=null
    var listMag=ArrayList<UserInfo>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance().currentUser!!.uid
        ok=true
        loadMag()
        myadapter=MsgAdapter(this!!.activity!!,listMag)
        foodprivder.adapter=myadapter//pl
        profileRes.setOnClickListener{

            view.findNavController().navigate(R.id.action_displayList_to_custmerpage)

        }
    }

    private fun loadMag(){//this shoud put in load page
        //form databae and shound makeing in order
        /*
           listMag.add(Msg("","HANA ALMADI",1/2/1,"HI HOW ARE YOU",R.drawable.me,R.drawable.white))
        listMag.add(Msg("","saad ALMADI",1/11/1,"HI HOW ARE YOU",R.drawable.me,R.drawable.logo))*/
        var oldMsg:UserInfo
        var newMs:UserInfo
        lateinit var  last: DataSnapshot
        var database = FirebaseDatabase.getInstance().getReference("UserInfo")
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()}
            override fun onDataChange(p0: DataSnapshot) {

                if (p0.exists()) {

                    for (e in p0.children) {
                        if(ok){
                            var oldMsg = e.getValue(UserInfo::class.java) as UserInfo
                            listMag.add(oldMsg)//sort }
                            //lastone
                        }
                        last=e
                    }
                   // ok=false
                    if(!ok){
                        var newMs = last.getValue(UserInfo::class.java) as UserInfo
                        listMag.add(newMs)//sort
                    }



                    myadapter!!.notifyDataSetChanged()

                }}})}


    inner class MsgAdapter: BaseAdapter {
        var context: Context?=null
        var listOfMagsLocal=ArrayList<UserInfo>()
        constructor(context: Context, listOfMags:ArrayList<UserInfo>){
            listOfMagsLocal=listOfMags
            this.context=context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var Masobject=listOfMagsLocal[position]
            var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val magView=inflator.inflate(R.layout.listfoodprivder,null)
            magView.nameresCut.text=Masobject.username!!
            //magView.dataall.text= Masobject.date.toString()!!
            //magView.textall.text=Masobject.text!!
            //  magView.phtotoall.setImageResource(Masobject.imageText)
            return magView
        }

        override fun getItem(position: Int): Any {
            return listOfMagsLocal[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfMagsLocal.size
        }

    }

    private fun add(msg:UserInfo){
        // listMag.add(msg)
        val database=FirebaseDatabase.getInstance().getReference("UserInfo")
        val userkey=database.push().key
        database.child(userkey!!).setValue(msg).addOnCompleteListener { task->
            if (task.isSuccessful) {
                var g=true
                //Toast.makeText(context, " YSE isSuccessful", Toast.LENGTH_SHORT).show()

                }
            else{
                Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()}
        }
        // myadapter!!.notifyDataSetChanged()
        //inset in database
    }
}
