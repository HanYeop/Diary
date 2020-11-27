package org.techtown.diary

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val items: ArrayList<Note>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>(), OnNoteItemClickListener{

    lateinit var listener : OnNoteItemClickListener

    private var layoutType = 0

    override fun getItemCount() = items.size

    fun getItem(position: Int): Note? {
        return items[position]
    }

    fun addItem(item: Note?) {
        if (item != null) {
            items.add(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item,parent,false)
        return ViewHolder(inflatedView,this,layoutType) // 보류
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

    class ViewHolder(itemView : View, listener: OnNoteItemClickListener, layoutType : Int) : RecyclerView.ViewHolder(itemView){

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

                itemView.pictureExistsImageView.visibility = View.VISIBLE
                itemView.pictureImageView.visibility = View.VISIBLE
                itemView.pictureImageView.setImageURI(Uri.parse("file://$picturePath"))
            }
            else{
                itemView.pictureExistsImageView.visibility = View.GONE
                itemView.pictureImageView.visibility = View.GONE
                itemView.pictureImageView.setImageResource(R.drawable.noimagefound)
            }

            var weather = item.weather
            var weatherIndex = Integer.parseInt(weather)
            setWeatherImage(weatherIndex)

            itemView.contentsTextView.text = item.contents
            itemView.contentsTextView2.text = item.contents

            itemView.locationTextView.text = item.address
            itemView.locationTextView2.text = item.address

            itemView.dateTextView.text = item.createDataStr
            itemView.dateTextView2.text = item.createDataStr
        }

        fun setMoodImage( moodIndex : Int){
            when(moodIndex){
                0 -> {
                    itemView.moodImageView.setImageResource(R.drawable.smile1_48)
                    itemView.moodImageView2.setImageResource(R.drawable.smile1_48)
                }
                1 -> {
                    itemView.moodImageView.setImageResource(R.drawable.smile2_48)
                    itemView.moodImageView2.setImageResource(R.drawable.smile2_48)
                }
                2 -> {
                    itemView.moodImageView.setImageResource(R.drawable.smile3_48)
                    itemView.moodImageView2.setImageResource(R.drawable.smile3_48)
                }
                3 -> {
                    itemView.moodImageView.setImageResource(R.drawable.smile4_48)
                    itemView.moodImageView2.setImageResource(R.drawable.smile4_48)
                }
                4 -> {
                    itemView.moodImageView.setImageResource(R.drawable.smile5_48)
                    itemView.moodImageView2.setImageResource(R.drawable.smile5_48)
                }
                else -> {
                    itemView.moodImageView.setImageResource(R.drawable.smile3_48)
                    itemView.moodImageView2.setImageResource(R.drawable.smile3_48)
                }
            }
        }

        fun setWeatherImage(weatherIndex : Int){
            when(weatherIndex){
                0 -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_1)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_1)
                }
                1 -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_2)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_2)
                }
                2 -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_3)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_3)
                }
                3 -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_4)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_4)
                }
                4 -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_5)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_5)
                }
                5 -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_6)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_6)
                }
                6 -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_7)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_7)
                }
                else -> {
                    itemView.weatherImageView.setImageResource(R.drawable.weather_icon_1)
                    itemView.weatherImageView2.setImageResource(R.drawable.weather_icon_1)
                }
            }
        }

        fun setLayoutType(layoutType: Int){
            if( layoutType == 0){
                itemView.layout1.visibility = View.VISIBLE
                itemView.layout2.visibility = View.GONE
            }
            else if( layoutType == 1){
                itemView.layout1.visibility = View.GONE
                itemView.layout2.visibility = View.VISIBLE
            }
        }
    }


}