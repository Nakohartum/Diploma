package com.example.diploma.models

import com.example.diploma.R
import java.util.Date

//object StubObjects{
//    val userData = UserData(
//        "1", "TestName", "TestSurname", Date(2001,11,24),"TestProfession", 1, R.drawable.stub_image
//    )
//    val listOfTeachers = listOf(
//        TeacherData("1", "Test Teacher Name", "8-888-888-88-88")
//    )
//    val listOfSubjects = listOf(
//        SubjectData(
//            "1",
//            "Test Subject",
//            "Test Description",
//            listOfTeachers[0],
//            R.drawable.stub_image,
//            listOf(
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 9,11),
//                    true
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                )
//            )
//        ),
//        SubjectData(
//            "1",
//            "Test Subject",
//            "Test Description",
//            listOfTeachers[0],
//            R.drawable.stub_image,
//            listOf(
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 9,11),
//                    true
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                )
//            )
//        ),
//        SubjectData(
//            "1",
//            "Test Subject",
//            "Test Description",
//            listOfTeachers[0],
//            R.drawable.stub_image,
//            listOf(
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 9,11),
//                    true
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                )
//            )
//        ),
//        SubjectData(
//            "1",
//            "Test Subject",
//            "Test Description",
//            listOfTeachers[0],
//            R.drawable.stub_image,
//            listOf(
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 9,11),
//                    true
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                ),
//                TargetData(
//                    "1",
//                    "Test target",
//                    "Test target descriptionTest target descriptionTest target descriptionTest target descriptionTest target description",
//                    Date(2024, 11,11)
//                )
//            )
//        )
//    )
//
//    val daysOfTheWeek = listOf(
//        DayOfWeek(1, "Monday", listOfSubjects)
//    )
//}