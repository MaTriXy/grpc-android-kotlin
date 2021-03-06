/*
 * Copyright (C) 2017 Jacksgong(blog.dreamtobe.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.dreamtobe.grpc.client.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.dreamtobe.grpc.client.R
import de.mkammerer.grpcchat.protocol.RoomMessage

/**
 * Created by Jacksgong on 08/03/2017.
 */
class ConversationListAdapter : RecyclerView.Adapter<ConversationListAdapter.ConversationListViewHolder>() {

    var conversationList = mutableListOf<RoomMessage>()
    var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ConversationListViewHolder {
        val itemView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_conversation, parent, false)
        val viewHolder = ConversationListViewHolder(itemView)
        viewHolder.contentLayout.setOnClickListener {
            callback?.onItemClick(viewHolder.roomMessage)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ConversationListViewHolder, position: Int) {
        val roomMessage = conversationList[position]
        holder.roomMessage = roomMessage

        holder.titleTv.text = roomMessage.title
        holder.descTv.text = roomMessage.desc
    }

    override fun getItemCount() = conversationList.size

    class ConversationListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentLayout = itemView.findViewById(R.id.content_layout)!!
        val titleTv: TextView = itemView.findViewById(R.id.title_tv) as TextView
        val descTv: TextView = itemView.findViewById(R.id.desc_tv) as TextView

        lateinit var roomMessage : RoomMessage
    }

    interface Callback {
        fun onItemClick(roomMessage: RoomMessage)
    }
}