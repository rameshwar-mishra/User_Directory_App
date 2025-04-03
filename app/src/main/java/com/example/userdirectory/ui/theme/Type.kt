package com.example.userdirectory.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val customTypo = Typography(
    bodyLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

)

val customTypography = CustomTypography(
    cardTitle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    searchPlaceholder = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    customButtonText = TextStyle(
        fontFamily = FontFamily.Monospace ,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    customContentNotAvailableText = TextStyle(
        fontFamily = FontFamily.SansSerif ,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp
    ),
    textStyleUserDetails = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    )
)


