import Foundation
import Testing
import MEGAAnalyticsiOS

/// End-to-end check that the Tracker can call back into Swift from Kotlin's background threads.
struct TrackerTests {

    @Test func trackedEventReachesTheEventSender() async throws {
        let sender = RecordingEventSender()
        let tracker = Tracker(
            viewIdProvider: FixedViewIdProvider(viewId: "smoke-test-view"),
            appIdentifier: AppIdentifier(id: 1),
            eventSender: sender
        )

        tracker.trackEvent(eventIdentifier: PhotoScreenEvent.shared)

        let sent = try #require(await sender.firstEvent(timeout: 10))
        #expect(sent.eventId > 0)
        #expect(sent.viewId == "smoke-test-view")
        #expect(!sent.message.isEmpty)
    }
}

private final class FixedViewIdProvider: NSObject, ViewIdProvider {
    private let viewId: String

    init(viewId: String) {
        self.viewId = viewId
    }

    func getViewIdentifier(completionHandler: @escaping (String?, (any Error)?) -> Void) {
        completionHandler(viewId, nil)
    }
}

private final class RecordingEventSender: NSObject, EventSender {
    struct SentEvent {
        let eventId: Int32
        let message: String
        let viewId: String?
    }

    private let lock = NSLock()
    private var events = [SentEvent]()

    func sendEvent(eventId: Int32, message: String, viewId: String?) {
        lock.lock()
        events.append(SentEvent(eventId: eventId, message: message, viewId: viewId))
        lock.unlock()
    }

    func firstEvent(timeout seconds: TimeInterval) async -> SentEvent? {
        let deadline = Date().addingTimeInterval(seconds)
        while Date() < deadline {
            if let first = recordedEvents().first { return first }
            try? await Task.sleep(nanoseconds: 50_000_000)
        }
        return nil
    }

    private func recordedEvents() -> [SentEvent] {
        lock.withLock { events }
    }
}
