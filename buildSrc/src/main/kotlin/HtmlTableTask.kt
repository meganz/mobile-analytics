package src.main.kotlin

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class HtmlTableTask : DefaultTask() {

    @get:Input
    abstract val inputPath: Property<String>

    @get:Input
    abstract val outputPath: Property<String>

    init {
        group = "build"
        description = "Generates HTML tables for JSON files"
        inputPath.convention("src/commonMain/resources")
        outputPath.convention("build/generated-html")
    }

    @TaskAction
    fun generateHtmlTables() {
        println("Generating html tables")
        val inputDir = project.file(inputPath)
        val outputDir = project.file(outputPath)
        outputDir.mkdirs()
        println("Input directory: ${inputDir.path}")

        println("Output directory: ${outputDir.path}")


        val jsonFiles: FileTree? = project.fileTree(inputDir).include("**/*.json") as? FileTree

        val indexHtml = buildString {
            appendLine("<html>")
            appendLine("<head>")
            appendLine("<title>Analytics Events</title>")
            appendLine("</head>")
            appendLine("<body>")
            appendLine("<h1>Events</h1>")
            appendLine("<ul>")


            jsonFiles?.forEach { jsonFile ->
                println("Resource file found: ${jsonFile.path}")

                val text = jsonFile.readText().takeUnless { it.isEmpty() }
                val jsonContent = text?.let { JsonSlurper().parseText(it) as? Map<*, *> }
                val tableName = jsonFile.nameWithoutExtension

                val htmlTable = buildString {
                    append(
                        """
                            <html>
                            <head>
                                <title>${tableName.camelToSentence()}</title>
                                <style>
                                    table {
                                        border-collapse: collapse;
                                        width: 100%;
                                    }
                                    th, td {
                                        border: 1px solid black;
                                        padding: 8px;
                                        text-align: left;
                                    }
                                </style>
                                <script>
                                    function filterTable() {
                                        var input, filter, table, tr, td, i, txtValue;
                                        input = document.getElementById("searchInput");
                                        filter = input.value.toUpperCase();
                                        table = document.getElementById("dataTable");
                                        tr = table.getElementsByTagName("tr");
                                        for (i = 0; i < tr.length; i++) {
                                            td = tr[i].getElementsByTagName("td")[0];
                                            if (td) {
                                                txtValue = td.textContent || td.innerText;
                                                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                                    tr[i].style.display = "";
                                                } else {
                                                    tr[i].style.display = "none";
                                                }
                                            }
                                        }
                                    }
                                </script>
                            </head>
                            <body>
                                <h1>${tableName.camelToSentence()}</h1>
                                <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search...">
                                <table id="dataTable">
                                    <tr>
                                        <th>Event Name</th>
                                        <th>Android event Id</th>
                                        <th>iOS event Id</th>
                                    </tr>
                        """
                    )


                    jsonContent?.forEach { key, value ->
                        append(
                            """
                                <tr>
                                    <td>${key}</td>
                                    <td>${getAndroidIdentifier(value, tableName)}</td>
                                    <td>${getiOSIdentifier(value, tableName)}</td>
                                </tr>
                            """
                        )
                    }

                    append(
                        """
                            </table>
                            </body>
                            </html>
                        """
                    )

                }
                val outputFile = File(outputDir, "${tableName}.html")
                outputFile.writeText(htmlTable.toString())

                val csvFile = File(outputDir, "${tableName}.csv")
                csvFile.bufferedWriter().use { writer ->
                    // Generate CSV content...
                    jsonContent?.forEach { key, value ->
                        writer.write("${key},${getAndroidIdentifier(value, tableName)},${getiOSIdentifier(value, tableName)}")
                        writer.newLine()
                    }
                }

                appendLine("<li>")
                appendLine("<a href=\"${tableName}.html\">${tableName.camelToSentence()}</a>")
                appendLine("<a href=\"${tableName}.csv\" download>Download CSV</a>")
                appendLine("</li>")
            }
            appendLine("</ul>")
            appendLine("</body>")
            appendLine("</html>")
        }

        val indexFile = File(outputDir, "index.html")
        indexFile.writeText(indexHtml)
    }

    fun String.camelToSentence(): String {
        val pattern = "(?<=.)[A-Z]".toRegex()
        return this.replace(pattern, " $0")
    }

    private fun getAndroidIdentifier(value: Any?, eventType: String): String {
        val id = value as? Int ?: return "null"
        val platform = 300_000
        val type = getType(eventType)
        return (id + platform + type).toString()
    }

    private fun getiOSIdentifier(value: Any?, eventType: String): String {
        val id = value as? Int ?: return "null"
        val platform = 400_000
        val type = getType(eventType)
        return (id + platform + type).toString()
    }

    private fun getType(eventType: String): Int {
        return when{
            eventType.contains("ScreenView", true) -> 0
            eventType.contains("TabSelected", true) -> 1000
            eventType.contains("ButtonPress", true) -> 2000
            eventType.contains("DialogDisplayed", true) -> 3000
            eventType.contains("Navigation", true) -> 4000
            eventType.contains("MenuItemSelec", true) -> 5000
            eventType.contains("Notification", true) -> 6000
            eventType.contains("General", true) -> 7000
            eventType.contains("ItemSelect", true) -> 8000
            else -> Int.MIN_VALUE
        }
    }

}
