package fr.iut.alldev.allin.test.finders

import androidx.compose.ui.test.*

fun SemanticsNodeInteraction.onChildWith(matcher: SemanticsMatcher) = onChildren().filterToOne(matcher)

fun SemanticsNodeInteraction.onChildWithTag(testTag: String) = onChildWith(hasTestTag(testTag))