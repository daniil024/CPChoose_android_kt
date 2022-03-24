package com.hsecourseproject.cpchoose.cplist.models.enums

enum class CPStatus {
    CREATED,
    ONAPPROVING,
    // This status indicate that CP is available to choose
    APPROVED,
    SELECTED,
    PARTIALLYSELECTED
}