# Mobile Analytics

## Integrating the library
1. Implement the `EventSender` and `ViewIdProvider` interfaces
2. Create a singleton instance of the `Tracker` class
3. Pass the generated event identifier objects to the `Tracker` instance `trackEvent` method

The `sendEvent` method will be called on a background thread by the `Tracker` class.

## Adding an event
1. Create an interface/class and annotate it with the relevant annotation (See below)
2. Run `gradlew build`
3. Commit newly created interface/class as well as the generated/updated json file in the resources folder
 **NB! If the json file is not committed the event id might change causing the analytics data to become corrupted!**

## Examples
The below examples can also be found in the `Examples.kt` file in the `shared` module
```
/**
* Excluded class
*
* Any class decorated with [Exclude] will not be generated.
* [Exclude] can also be applied to a file to exclude all classes in that file
  */
  @Exclude
  @ScreenViewEvent
  interface ExcludedClass

/**
* Example button press
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
*
* Button name is required
* Screen and Dialog names are optional
  */
  @ButtonPressEvent(buttonName = "ButtonName", screen = "ScreenName", dialog = "DialogName")
  interface ExampleButtonPress

/**
* Example dialog displayed
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
*
* Dialog name is required
* Screen name is optional
  */
  @DialogDisplayedEvent(dialog = "DialogName", screen = "ScreenName")
  interface ExampleDialogDisplayed

/**
* Example simple general event
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
  */
  @GeneralEvent
  interface ExampleSimpleGeneralEvent

/**
* Example complex general event
*
* Generated event type is class
* Event name will the [class name], event class will be [class name + "Event"]
*
* Any Parameters defined will be added as parameters to the class. Values passed at runtime will
* be added to the json payload map using the parameter name as key. Supported types are
* primitives as well as lists and maps. All other types will be represented by their toString()
* value.
*
* Parameters annotated with @StaticValue will not be added to class parameters, only to the json
* payload map.
*
* Below example will generate the following:
* class ExampleComplexGeneralEventEvent: GeneralEventIdentifier(
*           val foo: String?,
*           val bar: Int,
*      ){
*           ...
*           override val info: Map<String, Any?> = mapOf(
*           "foo" to foo,
*           "bar" to bar,
*           "fooBar" to "22"
*           )
*           ...
*      }
*/
@GeneralEvent
class ExampleComplexGeneralEvent(
val foo: String?,
val bar: Int,
@StaticValue("22")
val fooBar: Int, //Type does not matter, this will always default to string.
)

/**
* Example simple item selected event
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
  */
  @GeneralEvent
  interface ExampleSimpleItemSelected

/**
* Example complex item selected event
*
* Generated event type is class
* Event name will the [class name], event class will be [class name + "Event"]
*
* Any Parameters defined will be added as parameters to the class. Values passed at runtime will
* be added to the json payload map using the parameter name as key. Supported types are
* primitives as well as lists and maps. All other types will be represented by their toString()
* value.
*
* Parameters annotated with @StaticValue will not be added to class parameters, only to the json
* payload map.
*
* Below example will generate the following:
* class ExampleComplexItemSelectedEvent: ItemSelectedEventIdentifier(
*           val foo: String?,
*           val bar: Int,
*      ){
*           ...
*           override val info: Map<String, Any?> = mapOf(
*           "foo" to foo,
*           "bar" to bar,
*           "fooBar" to "22"
*           )
*           ...
*      }
*/
@GeneralEvent
class ExampleComplexItemSelected(
val foo: String?,
val bar: Int,
@StaticValue("22")
val fooBar: Int, //Type does not matter, this will always default to string.
)

/**
* Example menu item selected
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
*
* Menu item name is required
* Menu type is required. Options are
*  [MenuItemEvent.MenuType.Toolbar]
*  [MenuItemEvent.MenuType.Item]
* Screen name is optional
  */
  @MenuItemEvent(
  menuItem = "MenuItemName",
  menuType = MenuItemEvent.MenuType.Toolbar,
  screen = "ScreenName"
  )
  interface ExampleMenuItemSelected

/**
* Example navigation
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
*
* Destination name is required
* Navigation element type is required. Options are:
*  [NavigationEvent.NavigationElementType.Bottom]
*  [NavigationEvent.NavigationElementType.Drawer]
*  [NavigationEvent.NavigationElementType.Toolbar]
*  [NavigationEvent.NavigationElementType.System]
*  [NavigationEvent.NavigationElementType.Other]
   */
   @NavigationEvent(
   destination = "DestinationName",
   navigationElementType = NavigationEvent.NavigationElementType.Drawer
   )
   interface ExampleNavigation

/**
* Example notification
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
  */
  @NotificationEvent
  interface ExampleNotification

/**
* Example screen view event
*
* Generated event type is object
* Event name will the [interface name] - Use the screen name as interface name
* Event class will be [interface name + "Event"]
  */
  @ScreenViewEvent
  interface ExampleScreen

/**
* Example tab selected event
*
* Generated event type is object
* Event name will the [interface name], event class will be [interface name + "Event"]
*
* Screen name is required
* Tab name is required
  */
  @TabSelectedEvent(screenName = "Screen Name", tabName = "Tab name")
  interface ExampleTabSelected
```