package com.andrei.wegroszta.dailygratitudelist.list.io.remote

import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import com.andrei.wegroszta.dailygratitudelist.list.data.GratitudeListRepository.GratitudeListRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DummyGratitudeListRemoteDataSource : GratitudeListRemoteDataSource {

    private val format = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    override suspend fun load(): List<Gratitude> {
        delay(2000L)
        val networkGratitudes: List<NetworkGratitude> = format.decodeFromString(DUMMY_DATA)
        return networkGratitudes.map { it.toGratitude() }
    }

    companion object {
        private const val DUMMY_DATA =
            """
[
   {
      "id":"bla1",
      "date":1662744437000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo.",
      "images":[
         "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Labrador_on_Quantock_%282175262184%29.jpg/1200px-Labrador_on_Quantock_%282175262184%29.jpg",
         "https://ichef.bbci.co.uk/news/976/cpsprodpb/17638/production/_124800859_gettyimages-817514614.jpg",
         "https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg",
         "https://ichef.bbci.co.uk/news/976/cpsprodpb/5899/production/_126418622_gettyimages-175597750-1.jpg",
         "https://dynaimage.cdn.cnn.com/cnn/c_fill,g_auto,w_1200,h_675,ar_16:9/https%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F220719101500-keeping-dogs-cool-explainer-wellness-stock.jpg",
         "https://www.purina-arabia.com/sites/default/files/2020-12/Dog_1098119012_Hero.jpg",
         "https://www.dog.com/ezine/hash_F4-F6-05-09-3F-BD-2D-49-64-B3-1A-FB-B3-FB-F2-8E/images/Homepage/2022/20220415D/images/feature1.jpg",
         "https://cdn.cnn.com/cnnnext/dam/assets/201030094143-stock-rhodesian-ridgeback-super-tease.jpg",
         "https://wolf.org/wp-content/uploads/2020/10/Spartacus_Mid-Content-copy.jpg",
         "https://cdn.britannica.com/48/167648-131-9CD98F23/Shiba-inu.jpg",
         "https://www.allarts.org/wp-content/uploads/2022/08/dog-tales.jpeg"
      ],
      "tags":[
         "dogs",
         "nature",
         "animals",
         "friends",
         "tag name 1",
         "tag name 2",
         "tag name 3",
         "tag name 4",
         "tag name 5",
         "tag name 6",
         "tag name 7",
         "tag name 8",
         "tag name 9",
         "tag name 10"
      ]
   },
   {
      "id":"bla2",
      "date":1663176437000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla3",
      "date":1663090037000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla4",
      "date":1663003637000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo.",
      "tags":[
         "tag name 11",
         "tag name 12",
         "tag name 13",
         "tag name 14",
         "tag name 15",
         "tag name 16",
         "tag name 17",
         "tag name 18",
         "tag name 19",
         "tag name 20"
      ]
   },
   {
      "id":"bla5",
      "date":1662917237000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo.",
      "images":[
         "https://ichef.bbci.co.uk/news/976/cpsprodpb/17638/production/_124800859_gettyimages-817514614.jpg",
         "https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg",
         "https://ichef.bbci.co.uk/news/976/cpsprodpb/5899/production/_126418622_gettyimages-175597750-1.jpg",
         "https://dynaimage.cdn.cnn.com/cnn/c_fill,g_auto,w_1200,h_675,ar_16:9/https%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F220719101500-keeping-dogs-cool-explainer-wellness-stock.jpg",
         "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Labrador_on_Quantock_%282175262184%29.jpg/1200px-Labrador_on_Quantock_%282175262184%29.jpg",
         "https://www.purina-arabia.com/sites/default/files/2020-12/Dog_1098119012_Hero.jpg",
         "https://www.dog.com/ezine/hash_F4-F6-05-09-3F-BD-2D-49-64-B3-1A-FB-B3-FB-F2-8E/images/Homepage/2022/20220415D/images/feature1.jpg",
         "https://cdn.cnn.com/cnnnext/dam/assets/201030094143-stock-rhodesian-ridgeback-super-tease.jpg",
         "https://wolf.org/wp-content/uploads/2020/10/Spartacus_Mid-Content-copy.jpg",
         "https://cdn.britannica.com/48/167648-131-9CD98F23/Shiba-inu.jpg",
         "https://www.allarts.org/wp-content/uploads/2022/08/dog-tales.jpeg"
      ]
   },
   {
      "id":"bla6",
      "date":1663867637000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla7",
      "date":1663781237000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla8",
      "date":1663694837000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla9",
      "date":1661016437000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla10",
      "date":1660930037000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla11",
      "date":1661621237000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla12",
      "date":1661362037000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo.",
      "images":[
         "https://www.rd.com/wp-content/uploads/2021/01/GettyImages-1175550351.jpg",
         "https://cdn.britannica.com/39/7139-050-A88818BB/Himalayan-chocolate-point.jpg",
         "https://www.humanesociety.org/sites/default/files/styles/1240x698/public/2020-07/kitten-510651.jpg",
         "https://icatcare.org/app/uploads/2018/06/Layer-1704-1200x630.jpg",
         "https://images.livemint.com/img/2022/08/01/1600x900/Cat-andriyko-podilnyk-RCfi7vgJjUY-unsplash_1659328989095_1659328998370_1659328998370.jpg"
      ],
      "tags":[
         "cats",
         "nature",
         "animals",
         "friends",
         "tag name 21",
         "tag name 22",
         "tag name 23",
         "tag name 24",
         "tag name 25",
         "tag name 26",
         "tag name 27",
         "tag name 28",
         "tag name 29",
         "tag name 30"
      ]
   },
   {
      "id":"bla13",
      "date":1661275637000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla14",
      "date":1670607638000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   },
   {
      "id":"bla15",
      "date":1670953238000,
      "summary":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut risus eget nisl ornare aliquet. Quisque massa tellus, ornare vestibulum sagittis sed, porttitor eu metus. Duis auctor, ipsum ut dictum malesuada, leo lorem pharetra odio, eu lobortis lectus dolor at elit. Nunc vitae suscipit urna. Vivamus id tortor vitae nibh consequat porta ac ut neque. Nunc non volutpat est, vel venenatis ligula. Mauris ac consequat eros, et tristique mi. Quisque bibendum consectetur sapien in maximus. Etiam sodales purus est, quis lacinia nulla aliquam vel. Maecenas congue urna eget molestie pulvinar. Ut consectetur, ligula at commodo pellentesque, purus enim congue orci, non blandit metus dolor vel arcu. Maecenas facilisis venenatis iaculis. Duis eu bibendum leo."
   }
]
            """
    }
}
