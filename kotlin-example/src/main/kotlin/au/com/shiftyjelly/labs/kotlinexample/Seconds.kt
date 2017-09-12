package au.com.shiftyjelly.labs.kotlinexample

val Number.minutes: Long get() = this.toLong() / 60
val Number.hours: Long get() = this.minutes / 60