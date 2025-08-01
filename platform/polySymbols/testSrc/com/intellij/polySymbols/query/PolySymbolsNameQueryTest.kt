// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.polySymbols.query

import com.intellij.model.Pointer
import com.intellij.polySymbols.*
import com.intellij.polySymbols.html.HTML_ATTRIBUTES
import com.intellij.polySymbols.testFramework.query.doTest
import com.intellij.polySymbols.testFramework.query.printMatches
import com.intellij.polySymbols.webTypes.json.parseWebTypesPath

class PolySymbolsNameQueryTest : PolySymbolsMockQueryExecutorTestBase() {

  override val testPath: String = "$polySymbolsTestsDataPath/query/name"

  fun testBasicElement() {
    doTest("html/elements/foo", null, "basic")
  }

  fun testBasicAttribute() {
    doTest("html/elements/foo/attributes/foo", null, "basic")
  }

  fun testMultiElementAttribute() {
    doTest("html/elements/bar/attributes/foo", null, "basic")
  }

  fun testBasicElementSpecificAttribute() {
    doTest("html/elements/foo/attributes/bar", null, "basic")
  }

  fun testBasicPatternAttribute() {
    doTest("html/attributes/v-foo", null, true, "basic-pattern")
  }

  fun testVueDirectiveWithArguments() {
    doTest("html/elements/foo/attributes/v-on:bar.stop.foo.once", "vue", true, "vue")
  }

  fun testVueDirectiveWithArguments2() {
    doTest("html/elements/foo/attributes/v-on:click.stop", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWithArguments3() {
    doTest("html/elements/foo/attributes/v-on.stop", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWithArguments4() {
    doTest("html/elements/foo/attributes/v-on:click", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWithArguments5() {
    doTest("html/elements/foo/attributes/v-for.bar", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWithArguments6() {
    doTest("html/elements/foo/attributes/v-for:bar", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWith2Events() {
    doTest("html/elements/foo/attributes/v-on:2foo", "vue", true, "vue", "events")
  }

  fun testVueDirectiveShorthand1() {
    doTest("html/elements/foo/attributes/@2foo.stop.bar", "vue", true, "vue", "events")
  }

  fun testVueDirectiveShorthand2() {
    doTest("html/elements/foo/attributes/@click", "vue", true, "vue", "events")
  }

  fun testVueDirectiveShorthand3() {
    doTest("html/elements/foo/attributes/@.stop", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWithExtensions() {
    doTest("html/elements/foo/attributes/@click.enter.left.ctrl.exact.stop", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWithExtensions2() {
    doTest("html/elements/foo/attributes/v-on:keypress.enter.left.ctrl.exact.stop", "vue", true, "vue", "events")
  }

  fun testVueDirectiveWithExtensions3() {
    doTest("html/elements/foo/attributes/@drag.enter.left.ctrl.exact.stop", "vue", true, "vue", "events")
  }

  fun testVueElementWithExtends2() {
    doTest("html/elements/TransitionGroup/attributes/appearActiveClass", "vue", "vue")
  }

  fun testVueElementEvents1() {
    doTest("html/elements/TransitionGroup/attributes/v-on:appear.stop", "vue", true, "vue")
  }

  fun testVueElementEvents2() {
    doTest("html/elements/TransitionGroup/attributes/v-on:appear.stop.stop.prevent", "vue", true, "vue")
  }

  fun testVueElementBind1() {
    doTest("html/elements/TransitionGroup/attributes/v-bind:tag", "vue", true, "vue")
  }

  fun testOldVueElement1() {
    doTest("html/elements/TransitionGroupOld/attributes/v-on:appear.stop", "vue", true, "vue", "vue-old")
  }

  fun testOldVueElement2() {
    doTest("html/elements/TransitionGroupOld/attributes/moveClass", "vue", true, "vue", "vue-old")
  }

  fun testOldVueDirectiveWithArguments() {
    doTest("html/elements/foo/attributes/v-on_old:bar.stop.foo.once", "vue", true, "vue", "vue-old", "events")
  }

  fun testOldVueDirectiveWithArguments2() {
    doTest("html/elements/foo/attributes/v-on_old:click.stop", "vue", true, "vue", "vue-old", "events")
  }

  fun testCssProperties3() {
    doTest("html/elements/tag-with-css/css/properties/global-prop", null, true, "css")
  }

  fun testCssClasses2() {
    doTest("html/elements/tag-with-css/css/classes/class-from-tag", null, true, "css")
  }

  fun testComplexCssClasses1() {
    doTest("html/elements/tag-with-css/attributes/cls-attr-class", null, true, "css")
  }

  fun testComplexCssClasses2() {
    doTest("html/elements/tag-with-css/attributes/cls-class-from-tag", null, true, "css")
  }

  fun testComplexCssClasses3() {
    doTest("html/elements/tag-with-css/attributes/cls-global-class", null, true, "css")
  }

  fun testComplexCssClasses4() {
    doTest("html/elements/tag-with-css/attributes/cls-foo", null, true, "css")
  }

  fun testNgStyleAttrs1() {
    doTest("html/elements/tag-with-css/attributes/[prop1]", null, true, "ng-sample", "css")
  }

  fun testNgStyleAttrs2() {
    doTest("html/elements/tag-with-css/attributes/data-[prop1]", null, true, "ng-sample", "css")
  }

  fun testNgStyleAttrs3() {
    doTest("html/elements/tag-with-css/attributes/data-bind-prop1", null, true, "ng-sample", "css")
  }

  fun testNgStyleAttrs4() {
    doTest("html/elements/tag-with-css/attributes/data-bind-class.class-from-tag", null, true, "ng-sample", "css")
  }

  fun testNgStyleAttrs5() {
    doTest("html/elements/tag-with-css/attributes/bind-class.attr-class", null, true, "ng-sample", "css")
  }

  fun testNgStyleAttrs6() {
    doTest("html/elements/tag-with-css/attributes/bind-attr.attr1", null, true, "ng-sample", "css")
  }

  fun testNgStyleAttrs7() {
    doTest("html/elements/tag-with-css/attributes/[attr1]", null, true, "ng-sample", "css")
  }

  fun testNgStyleAttrs8() {
    doTest("html/elements/tag-with-css/attributes/(event1)", null, true, "ng-sample")
  }

  fun testNgEventBinding1() {
    doTest("html/attributes/(event1)", null, true, "ng-event-binding")
  }

  fun testNgEventBinding2() {
    doTest("html/attributes/(my-event)", null, true, "ng-event-binding")
  }

  fun testNgEventBinding3() {
    doTest("html/attributes/(bad-event)", null, true, "ng-event-binding")
  }

  fun testNgEventBinding4() {
    doTest("html/attributes/(foo)", null, true, "ng-event-binding")
  }

  fun testNgEventBinding5() {
    doTest("html/attributes/(fooin)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt1() {
    doTest("html/attributes/(keydown.shift.enter)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt2() {
    doTest("html/attributes/(keydown.enter)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt3() {
    doTest("html/attributes/(keydown.alt.shift.enter)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt4() {
    doTest("html/attributes/(keydown.alt.alt.enter)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt5() {
    doTest("html/attributes/(keydown.alt)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt6() {
    doTest("html/attributes/(keydown.alt.foo)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt7() {
    doTest("html/attributes/(keydown.foo)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt8() {
    doTest("html/attributes/(keydown.f1)", null, true, "ng-event-binding")
  }

  fun testNgEventBindingExt9() {
    doTest("html/attributes/(keyup.alt.shift.0)", null, true, "ng-event-binding")
  }

  fun testNgCustomEventBinding1() {
    doTest("html/attributes/(click.prevent)", null, true, "ng-event-binding", "events", "ng-custom-user-events")
  }

  fun testNgCustomEventBinding2() {
    doTest("html/attributes/(click.prevented)", null, true, "ng-event-binding", "events", "ng-custom-user-events")
  }

  fun testNgCustomEventBinding3() {
    doTest("html/attributes/(click.prevent.stop.prevent)", null, true, "ng-event-binding", "events", "ng-custom-user-events")
  }

  fun testNgCustomEventBinding4() {
    doTest("html/attributes/(clicked.prevent)", null, true, "ng-event-binding", "events", "ng-custom-user-events")
  }

  fun testNamingRules1() {
    doTest("html/elements/hello-11Wo-rld", "vue", true, "naming-rules")
  }

  fun testNamingRules2() {
    doTest("html/elements/Hello-12Wo-rld", "vue", true, "naming-rules")
  }

  fun testNamingRules3() {
    doTest("html/elements/Hello-13-woRld", "vue", true, "naming-rules")
  }

  fun testNamingRules4() {
    doTest("js/custom-properties/prop-one", "vue", true, "naming-rules")
  }

  fun testNamingRules5() {
    doTest("js/custom-properties/propOne", "vue", true, "naming-rules")
  }

  fun testNamingRules6() {
    doTest("js/custom-properties/prop-two-three", "vue", true, "naming-rules")
  }

  fun testNestedNamingRules1() {
    doTest("html/elements/hello-11Wo-rld", "vue", true, "nested-naming-rules")
  }

  fun testNestedNamingRules2() {
    doTest("html/elements/hello-13Wo-rld", "vue", true, "nested-naming-rules")
  }

  fun testLegacyVuetifyComponentProps2() {
    doTest("html/elements/VAutocomplete/attributes/allowOverflow", "vue", false, "vuetify-legacy", "vue")
  }

  fun testFilter1() {
    doTest("html/attributes/v-the-a", null, false, "filter")
  }

  fun testFilter2() {
    doTest("html/attributes/v-oh-c", null, false, "filter")
  }

  fun testLegacyDocVueComponent() {
    doTest("html/elements/Foo", "vue", "vue", "doc-legacy")
  }

  fun testLegacyDocVueComponentAttribute() {
    doTest("html/elements/Foo/attributes/v-bind:my-input", "vue", true, "vue", "doc-legacy")
  }

  fun testLegacyDocVueComponentEvent() {
    doTest("html/elements/Foo/attributes/@event1", "vue", true, "vue", "doc-legacy")
  }

  fun testLegacyDocVueComponentPatternSlot() {
    doTest("html/elements/Foo/slots/abaa", "vue", "vue", "doc-legacy")
  }

  fun testLegacyDocVueComponentSlot() {
    doTest("html/elements/Foo/slots/foo", "vue", "vue", "doc-legacy")
  }

  fun testLegacyDocVueDirective1() {
    doTest("html/attributes/v-cool:foo.foo.ba456", "vue", true, "vue", "doc-legacy")
  }

  fun testLegacyDocVueDirective2() {
    doTest("html/attributes/v-cool.foo.ba456", "vue", true, "vue", "doc-legacy")
  }

  fun testLegacyDocVueDirective3() {
    doTest("html/attributes/v-cool:foo", "vue", true, "vue", "doc-legacy")
  }

  fun testLegacyDocVueDirective4() {
    doTest("html/attributes/v-cool", "vue", true, "vue", "doc-legacy")
  }

  fun testLegacyDocVueDirective5() {
    doTest("html/attributes/v-cool.bc455", "vue", true, "vue", "doc-legacy")
  }

  fun testOptionalPatternNoRepeat1() {
    doTest("html/elements/icon-vuetify", null, "optional-pattern-no-repeat")
  }

  fun testOptionalPatternNoRepeat2() {
    doTest("html/elements/icon-vue-big", null, "optional-pattern-no-repeat")
  }

  fun testOptionalPatternNoRepeat3() {
    doTest("html/elements/icon-vuetfy-small", null, "optional-pattern-no-repeat")
  }

  fun testMixedFrameworkFiles() {
    doTest("html/elements/demo-test/attributes/foo-baz", "@polymer/polymer", "mixed-component", "mixed-framework")
  }

  fun testReferenceNameConversion() {
    doTest("html/elements/demo-test/attributes/foo-baz", "@polymer/polymer", "mixed-component", "reference-with-name-conversion")
  }

  fun testReferenceComplexNameConversion() {
    doTest("html/elements/demo-test/attributes/foo-FOO-BAR", null, includeVirtual = true, "mixed-component",
           "reference-with-complex-name-conversion")
  }

  fun testReferenceComplexNameConversion2() {
    doTest("html/elements/demo-test/attributes/foo-foo-bar", null, includeVirtual = true, "mixed-component",
           "reference-with-complex-name-conversion")
  }

  fun testMultipleReferences1() {
    doTest("html/attributes/test-the-a", null, "multiple-references")
  }

  fun testMultipleReferences2() {
    doTest("html/attributes/test-the-b", null, "multiple-references")
  }

  fun testBasicCustomElementsManifest1() {
    doTest("html/elements/my-EleMeNt", customElementsManifests = listOf("basic"))
  }

  fun testBasicCustomElementsManifest2() {
    doTest("html/elements/my-EleMeNt/attributes/disabled", customElementsManifests = listOf("basic"))
  }

  fun testCssClassListSingleClass() {
    doTest("css/class-list/foo", null, "css-class-list")
  }

  fun testCssClassListMultipleClasses1() {
    doTest("css/class-list/foo bar", null, "css-class-list")
  }

  fun testCssClassListMultipleClasses2() {
    doTest("css/class-list/foo-bar bar foo foo", null, "css-class-list")
  }

  fun testNestedPattern1() {
    polySymbolQueryExecutorFactory.addScope(
      object : PolySymbolScope {
        override fun getMatchingSymbols(
          qualifiedName: PolySymbolQualifiedName,
          params: PolySymbolNameMatchQueryParams,
          stack: PolySymbolQueryStack,
        ): List<PolySymbol> {
          return if (qualifiedName.qualifiedKind == HTML_ATTRIBUTES) {
            listOf(object : PolySymbol {
              override val origin: PolySymbolOrigin
                get() = object : PolySymbolOrigin {
                  override val framework: FrameworkId get() = "vue"
                }
              override val qualifiedKind: PolySymbolQualifiedKind
                get() = HTML_ATTRIBUTES
              override val name: String
                get() = "bar"

              override fun createPointer(): Pointer<out PolySymbol> = Pointer.hardPointer(this)

            })
          }
          else emptyList()
        }

        override fun createPointer(): Pointer<out PolySymbolScope> = Pointer.hardPointer(this)

        override fun getModificationCount(): Long = 0
      }, null, testRootDisposable)
    doTest("html/elements/bar-bar", "vue", true, "nested-pattern")
  }

  fun doTest(path: String, framework: String?, vararg webTypes: String) {
    doTest(path, framework, false, *webTypes)
  }

  fun doTest(path: String, framework: String?, includeVirtual: Boolean, vararg webTypes: String) {
    doTest(path, framework, includeVirtual, webTypes = webTypes.toList())
  }

  fun doTest(
    path: String,
    framework: String? = null,
    includeVirtual: Boolean = true,
    webTypes: List<String> = emptyList(),
    customElementsManifests: List<String> = emptyList(),
  ) {
    doTest(testPath) {
      registerFiles(framework, webTypes, customElementsManifests)
      val matches = polySymbolQueryExecutorFactory.create(null)
        .nameMatchQuery(parseWebTypesPath(path, null)) {
          if (!includeVirtual) exclude(PolySymbolModifier.VIRTUAL)
          exclude(PolySymbolModifier.ABSTRACT)
        }
      printMatches(matches, PolySymbolsTestsDebugOutputPrinter)
    }
  }

}