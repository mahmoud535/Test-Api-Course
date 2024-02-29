package com.example.testapicourse.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapicourse.domain.model.User
import com.example.testapicourse.databinding.ListItemsBinding
import com.squareup.picasso.Picasso

class NoteRecyclerView:RecyclerView.Adapter<NoteRecyclerView.NoteViewHolder> (){

   private lateinit var binding: ListItemsBinding
   var onListItemClick:OnListItemClick?= null
   var userList : List<User> = emptyList()

    fun setList(userList:List<User>){
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ListItemsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User){
            binding.ivItemUsername.text = user.name
            binding.ivItemMessage.text = user.message
            Picasso.get().load(user.imageId).into(binding.ivItemUserImage)

//            Glide.with(itemView.context).load(user.imageId).into(binding.ivItemUserImage)

            itemView.setOnClickListener {
                onListItemClick?.onItemClick(user)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val user: User =  userList[position]
        holder.bind(user)
    }
}