package components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        h1 { +"Willkommen bei der Schulungsmanagement-Anwendung" }
        // Hier können weitere UI-Komponenten hinzugefügt werden
    }
}
