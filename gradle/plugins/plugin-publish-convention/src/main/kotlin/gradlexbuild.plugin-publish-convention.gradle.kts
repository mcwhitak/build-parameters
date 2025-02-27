import gradlexbuild.pluginpublishconventions.PluginPublishConventionExtension

plugins {
    id("com.gradle.plugin-publish")
    id("signing")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

val pluginPublishConvention = extensions.create<PluginPublishConventionExtension>(
    PluginPublishConventionExtension.NAME, project, gradlePlugin, pluginBundle
)

publishing.publications.withType<MavenPublication>().configureEach {
    pom.name.set(pluginPublishConvention.displayName)
    pom.description.set(pluginPublishConvention.description)
    pom.url.set(pluginPublishConvention.gitHub)
    pom.licenses {
        // License could be configurable
        license {
            name.set("Apache-2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        }
    }
    pom.developers {
        // Developers could be configurable
        developer {
            id.set("britter")
            name.set("Benedikt Ritter")
            email.set("benedikt@gradlex.org")
        }
        developer {
            id.set("jjohannes")
            name.set("Jendrik Johannes")
            email.set("jendrik@gradlex.org")
        }
    }
    pom.scm {
        url.set(pluginPublishConvention.gitHub)
    }
}
