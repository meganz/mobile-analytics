// swift-tools-version:5.10
import PackageDescription

// Smoke tests that consume the generated Swift package exactly as the iOS app does.
// Run `./gradlew createSwiftPackage` first, then `swift test` from this directory.
let package = Package(
    name: "MEGAAnalyticsSmokeTests",
    platforms: [
        .macOS(.v14),
        .iOS(.v15),
    ],
    dependencies: [
        .package(path: "../SwiftPackages/MEGAAnalyticsiOS"),
    ],
    targets: [
        .testTarget(
            name: "MEGAAnalyticsSmokeTests",
            dependencies: [
                .product(name: "MEGAAnalyticsiOS", package: "MEGAAnalyticsiOS"),
            ]
        ),
    ]
)
