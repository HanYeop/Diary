package org.techtown.diary

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val items: ArrayList<Note>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>(), OnNoteItemClickListener{

    var listener : OnNoteItemClickListener? = null

    private var layoutType = 0

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item,parent,false)
        return NoteAdapter.ViewHolder(inflatedView,this,layoutType)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.setLayoutType(layoutType)
    }

    fun setOnItemClickListener(listener: OnNoteItemClickListener){
        this.listener = listener
    }
    override fun onItemClick(holder: ViewHolder, view: View, position: Int) {
        listener?.onItemClick(holder,view,position)
    }

    fun switchLayout(position: Int){
        layoutType = position
    }

    companion object class ViewHolder(itemView : View, listener: OnNoteItemClickListener, layoutType : Int) : RecyclerView.ViewHolder(itemView){
        private var layout1 = itemView.findViewById<LinearLayout>(R.id.layout1)
        private var layout2 = itemView.findViewById<LinearLayout>(R.id.layout2)

        private var moodImageView = itemView.findViewById<ImageView>(R.id.moodImageView)
        private var moodImageView2 = itemView.findViewById<ImageView>(R.id.moodImageView2)

        private var pictureExistsImageView = itemView.findViewById<ImageView>(R.id.pictureExistsImageView)
        private var pictureImageView = itemView.findViewById<ImageView>(R.id.pictureImageView)

        private var weatherImageView = itemView.findViewById<ImageView>(R.id.weatherImageView)
        private var weatherImageView2 = itemView.findViewById<ImageView>(R.id.weatherImageView2)

        private var contentsTextView = itemView.findViewById<TextView>(R.id.contentsTextView)
        private var contentsTextView2 = itemView.findViewById<TextView>(R.id.contentsTextView2)

        private var locationTextView = itemView.findViewById<TextView>(R.id.locationTextView)
        private var locationTextView2 = itemView.findViewById<TextView>(R.id.locationTextView2)

        private var dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)
        private var dateTextView2 = itemView.findViewById<TextView>(R.id.dateTextView2)

        init {
            itemView.setOnClickListener(View.OnClickListener {
                var position = adapterPosition
                listener?.onItemClick(this,itemView,position)
                setLayoutType(layoutType)
            })
        }

        fun bind(item : Note){
            var mood = item.mood
            var moodIndex = Integer.parseInt(mood)
            setMoodImage(moodIndex)

            var picturePath = item.picture
            if(picturePath != null && !picturePath.equals("")){
                pictureExistsImageView.visibility = View.VISIBLE
                pictureImageView.visibility = View.VISIBLE
                pictureImageView.setImageURI(Uri.parse("file://$picturePath"))
            }
            else{
                pictureExistsImageView.visibility = View.GONE
                pictureImageView.visibility = View.GONE
                pictureImageView.setImageResource(R.drawable.noimagefound)
            }

            var weather = item.weather
            var weatherIndex = Integer.parseInt(weather)
            setWeatherImage(weatherIndex)

            contentsTextView.text = item.contents
            contentsTextView2.text = item.contents

            locationTextView.text = item.address
            locationTextView2.text = item.address

            dateTextView.text = item.createDataStr
            dateTextView2.text = item.createDataStr
        }

        fun setMoodImage( moodIndex : Int){

        }

        fun setWeatherImage(weatherIndex : Int){

        }

        fun setLayoutType(layoutType: Int){
            if( layoutType == 0){
                layout1.visibility = View.VISIBLE
                layout2.visibility = View.GONE
            }
            else if( layoutType == 1){
                layout1.visibility = View.GONE
                layout2.visibility = View.VISIBLE
            }
        }
    }


}