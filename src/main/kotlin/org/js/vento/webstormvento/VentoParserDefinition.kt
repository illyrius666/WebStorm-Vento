/*
 * Copyright (c) 2023 Óscar Otero
 * All rights reserved.
 */

package org.js.vento.webstormvento

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

/**
 * Defines how Vento files are parsed, including which lexer to use,
 * how to create the PSI tree, and how to recognize comments or strings.
 */
class VentoParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = VentoLexerAdapter()
    override fun createParser(project: Project?): PsiParser = VentoParser()
    override fun getFileNodeType(): IFileElementType = IFileElementType(VentoLanguage)
    override fun getCommentTokens(): TokenSet = TokenSet.create(VentoTypes.COMMENT)
    override fun getStringLiteralElements(): TokenSet = TokenSet.create(VentoTypes.STRING)
    override fun createElement(node: ASTNode): PsiElement = VentoTypes.Factory.createElement(node)
    override fun createFile(viewProvider: FileViewProvider): PsiFile = VentoFile(viewProvider)
}