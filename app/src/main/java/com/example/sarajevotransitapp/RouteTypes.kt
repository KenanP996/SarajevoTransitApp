package com.example.sarajevotransitapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sarajevotransitapp.database.entities.routes
import com.example.sarajevotransitapp.database.functions.routetypes

class RouteTypes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_route_types)

        val listView: ListView = findViewById(R.id.list_view)
        val routeAdapter = RouteAdapter(this, routetypes.types_of_route())
        listView.adapter = routeAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            // Get selected item
            val route = routetypes.types_of_route()[position]

            // Start RouteActivity with selected route
            val intent = Intent(this, RouteActivity::class.java)
            intent.putExtra("route", route)
            startActivity(intent)
        }
    }

    private class RouteAdapter(context: Context, routes: List<routes>) :
        ArrayAdapter<routes>(context, 0, routes) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            val holder: ViewHolder

            if (view == null) {
                view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
                holder = ViewHolder()
                holder.routeNameTextView = view.findViewById(android.R.id.text1)
                holder.routeIdTextView = view.findViewById(android.R.id.text2)
                view.tag = holder
            } else {
                holder = view.tag as ViewHolder
            }

            val route = getItem(position)
            holder.routeNameTextView.text = route?.route_name
            holder.routeIdTextView.text = route?.route_id.toString()

            return view!!
        }

        private class ViewHolder {
            lateinit var routeNameTextView: TextView
            lateinit var routeIdTextView: TextView
        }
    }
}
