fun main(args: Array<String>) {
    val input = getResourceAsText("/input.txt")

    val pilot = Pilot()
    pilot.executeCommands(input)
    println(pilot)

    val improvedPilot = ImprovedPilot()
    improvedPilot.executeCommands(input)
    println(improvedPilot)
}

fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}