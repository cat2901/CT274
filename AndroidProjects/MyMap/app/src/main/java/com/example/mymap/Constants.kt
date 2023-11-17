package com.example.mymap

import com.example.mymap.Models.Place
import com.example.mymap.Models.UserMap

object Constants {
    fun generationSimpleData() : List<UserMap> {
        return listOf(
            UserMap(
                "Ẩm Thực",
                listOf(
                    Place("The 80's icafe", "Đường Mạc Thiên Tích", 10.0286827, 105.7732964),
                    Place("Trà sữa Tigon", "Đường Mạc Thiên Tích", 10.0278105, 105.7718373),
                    Place("Cafe Thủy Mộc", "Đường 3/2", 10.0273775, 105.7704913)
                )

            ),
            UserMap(
                "Đại Học Cần Thơ 2",
                listOf(
                    Place("Trường CNTT&TT", "thuộc ĐH Cần Thơ", 10.0308541, 105.768986),
                    Place("Trường Nông Nghiệp", "thuộc ĐH Cần Thơ", 10.0302655, 105.7679642),
                    Place("Hội Trường Rùa", "nơi tổ chức các hoạt động...", 10.0293402, 105.7690273)
                )

            )
        )
    }
}