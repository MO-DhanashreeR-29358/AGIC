package com.mosl.mobile.bytes


import io.reactivex.Observable


interface BytesRepository {
    fun getBytesList(): Observable<List<BytesHeader>>
}

object BytesRepositoryImpl : BytesRepository {
    override fun getBytesList(): Observable<List<BytesHeader>> {
        return headerObservable
    }

    private val headerObservable: Observable<List<BytesHeader>> by lazy {
        getAllHeaders()
    }


    private fun getAllHeaders(): Observable<List<BytesHeader>> {
        return Observable.fromCallable {
            val item1 = listOf(
                BytesData(
                    "4.42 pm",
                    "Day 3 ends now.Thank you for joining us.#MOAGIC"
                ),
                BytesData(
                    "4.18 pm",
                    "At HDFC life, we have design a new model to tap into the world of " +
                            "emergency ecosystem and partnership. Ms.Padalkar"
                ),
                BytesData(
                    "4.11 pm",
                    "Ms.Vibha Padalkar - MD & CEO,HDFC Life is speaking now at #MOAGIC"
                )
            )

            val group1 = BytesHeader("August 21,2019 04:00 pm", item1)


            val group2 = BytesHeader("August 21,2019 03:00 pm", item1)

            listOf(group1, group2)


        }
    }
}

