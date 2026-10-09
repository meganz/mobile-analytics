import Foundation
import Testing
import MEGAAnalyticsiOS

/// Events touched from Swift the way the iOS app and its tests do. Kotlin/Native 2.4.0 crashed in
/// `-[KotlinBase description]` when called from a thread the Kotlin runtime had not seen yet
/// (KT-86443), which took down the iOS unit-test host.
struct EventInteropTests {

    static let events: [any EventIdentifier] = [
        AcceptTermsOfServiceButtonPressedEvent.shared,
        PhotoScreenEvent.shared,
        VideoPlaybackRecordEvent(duration: 42),
    ]

    // Swift Testing describes each argument on its own task, so this covers the original crash.
    @Test(arguments: [
        AcceptTermsOfServiceButtonPressedEvent.shared,
        AcceptTermsOfServiceButtonPressedEvent.shared,
    ])
    func eventsCanBeUsedAsTestArguments(event: AcceptTermsOfServiceButtonPressedEvent) {
        #expect(!event.eventName.isEmpty)
    }

    @Test func eventsCanBeDescribedFromNewThreads() {
        let threadCount = 8
        let group = DispatchGroup()
        let lock = NSLock()
        var descriptions = [String]()

        for _ in 0..<threadCount {
            group.enter()
            // A fresh Thread has never called into Kotlin, unlike the shared cooperative pool.
            Thread {
                for event in Self.events {
                    let object = event as AnyObject
                    let text = "\(object)"
                    _ = object.hash
                    _ = object.isEqual(object)
                    _ = event.eventName
                    lock.lock()
                    descriptions.append(text)
                    lock.unlock()
                }
                group.leave()
            }.start()
        }

        #expect(group.wait(timeout: .now() + 30) == .success)
        #expect(descriptions.count == threadCount * Self.events.count)
        #expect(descriptions.allSatisfy { !$0.isEmpty })
    }

    @Test func singletonEventsAreStable() {
        #expect(PhotoScreenEvent.shared === PhotoScreenEvent.shared)
        #expect(PhotoScreenEvent.shared.isEqual(PhotoScreenEvent.shared))
    }

    @Test func parameterisedEventsExposeTheirInfo() {
        let event = VideoPlaybackRecordEvent(duration: 42)
        #expect(event.eventName == "VideoPlaybackRecord")
        #expect(event.info["duration"] as? Int32 == 42)
    }
}
